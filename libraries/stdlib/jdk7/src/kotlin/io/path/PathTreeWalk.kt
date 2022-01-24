/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:JvmMultifileClass
@file:JvmName("PathsKt")

package kotlin.io.path

import java.io.IOException
import java.nio.file.*


/**
 * An enumeration to describe possible walk options.
 * The options can be combined to get the walk order and behavior needed.
 *
 * Note that this enumeration is not exhaustive and new cases can be added in the future.
 */
public enum class PathWalkOption {
    /** Depth-first search, directory is visited BEFORE its entries */
    INCLUDE_DIRECTORIES,

    /** Breadth-first search, if combined with [INCLUDE_DIRECTORIES], directory and its siblings are visited BEFORE the directory entries */
    BFS,

    /** Symlinks are followed to the directories they point to */
    FOLLOW_LINKS
}

/**
 * This class is intended to implement different file traversal methods.
 * It allows to iterate through all files inside a given directory.
 * Iteration order of sibling files is unspecified.
 *
 * If the file path given is just a file, walker iterates only it.
 * If the file path given does not exist, walker iterates nothing, i.e. it's equivalent to an empty sequence.
 */
private class PathTreeWalk(
    private val start: Path,
    private val options: Array<out PathWalkOption>
) : Sequence<Path> {

    private val linkOptions: Array<LinkOption>
        get() = LinkFollowing.toOptions(followLinks = options.contains(PathWalkOption.FOLLOW_LINKS))

    private val excludeDirectories: Boolean
        get() = !options.contains(PathWalkOption.INCLUDE_DIRECTORIES)

    private val isBFS: Boolean
        get() = options.contains(PathWalkOption.BFS)


    /** Returns an iterator walking through files. */
    override fun iterator(): Iterator<Path> = if (isBFS) BFSPathTreeWalkIterator() else DSFPathTreeWalkIterator()

    private inner class DSFPathTreeWalkIterator : AbstractIterator<Path>() {

        // Stack of directory states, beginning from the start directory
        private val state = ArrayList<WalkState>()

        init {
            when {
                start.isDirectory(*linkOptions) -> state.add(DirectoryState(start))
                start.exists(LinkOption.NOFOLLOW_LINKS) -> state.add(SingleFileState(start))
                else -> done()
            }
        }

        override fun computeNext() {
            val next = gotoNext()
            if (next != null)
                setNext(next)
            else
                done()
        }

        private tailrec fun gotoNext(): Path? {
            // Take next file from the top of the stack or return if there's nothing left
            val topState = state.lastOrNull() ?: return null
            val path = topState.step()
            if (path == null) {
                // There is nothing more on the top of the stack, go back
                state.removeLast()
                return gotoNext()
            } else {
                // Check that file/directory matches the filter
                if (path == topState.root || !path.isDirectory(*linkOptions)) {
                    // Proceed to a root directory or a simple file
                    return path
                } else {
                    // Proceed to a sub-directory
                    state.add(DirectoryState(path))
                    return gotoNext()
                }
            }
        }

        /** Visiting in bottom-up order */
        private inner class DirectoryState(rootDir: Path) : WalkState(rootDir) {

            private var visitRoot = options.contains(PathWalkOption.INCLUDE_DIRECTORIES)

            private var fileList: List<Path>? = null

            private var fileIndex = 0

            private var failed = false

            override fun step(): Path? {
                if (visitRoot) {
                    visitRoot = false
                    // visit the root dir before entries
                    return root
                }
                if (!failed && fileList == null) {
                    try {
                        // TODO: the path may have been deleted using deleteRecursively applied to the parent path
                        fileList = root.listDirectoryEntries()
                    } catch (e: IOException) { // NotDirectoryException is also an IOException
                        failed = true
                        throw e
                    }
                }
                if (fileList != null && fileIndex < fileList!!.size) {
                    // visit all entries
                    return fileList!![fileIndex++]
                }

                // That's all
                return null
            }
        }

        private inner class SingleFileState(rootFile: Path) : WalkState(rootFile) {
            private var visited: Boolean = false

            init {
                assert(rootFile.exists(LinkOption.NOFOLLOW_LINKS)) { "rootFile must exist." }
            }

            override fun step(): Path? {
                if (visited) return null
                visited = true
                return root
            }
        }

        /** Abstract class that encapsulates file visiting in some order, beginning from a given [root] */
        private abstract inner class WalkState(val root: Path) {
            /** Call of this function proceeds to a next file for visiting and returns it */
            public abstract fun step(): Path?
        }
    }

    private inner class BFSPathTreeWalkIterator : AbstractIterator<Path>() {
        // Queue of entries to be visited. Entries at current depth are divided from the next depth entries by a `null`.
        private val queue = ArrayDeque<Path?>()

        init {
            queue.addLast(start)
            queue.addLast(null)
        }

        override fun computeNext() {
            val next = gotoNext()
            if (next != null)
                setNext(next)
            else
                done()
        }

        private tailrec fun gotoNext(): Path? {
            if (queue.isEmpty()) return null

            // TODO: the path may have been deleted using deleteRecursively applied to the parent path
            val path = queue.removeFirst()

            if (path == null) {
                if (queue.isNotEmpty()) {
                    // all entries in current depth were visited, separate entries at the next depth from their children
                    queue.addLast(null)
                }
                return gotoNext()
            }
            if (!path.isDirectory(*linkOptions)) {
                return path
            }
            val entries = path.listDirectoryEntries() // Don't catch IOExceptions
            queue.addAll(entries)
            return if (excludeDirectories) gotoNext() else path
        }
    }
}

/**
 * Returns a sequence for visiting this directory and all its content.
 *
 * By default, only files are visited, in depth-first search order, and symbolic links are not followed.
 * The combination of [options] overrides the default behavior. See [PathWalkOption].
 *
 * If the file located by this path does not exist, an empty sequence is returned.
 * if the file located by this path is not a directory, a sequence containing only this path is returned.
 */
public fun Path.walk(vararg options: PathWalkOption): Sequence<Path> = PathTreeWalk(this, options)

/**
 * Visits this directory and all its content with the specified [visitor].
 *
 * @param visitor the [FileVisitor] that receives callbacks.
 * @param maxDepth the maximum depth of a directory tree to traverse. By default, there is no limit.
 * @param followLinks specifies whether to follow symbolic links, `false` by default.
 */
public fun Path.visitFileTree(visitor: FileVisitor<Path>, maxDepth: Int = Int.MAX_VALUE, followLinks: Boolean = false): Unit {
    val options = if (followLinks) setOf(FileVisitOption.FOLLOW_LINKS) else setOf()
    visitFileTree(visitor, maxDepth, options)
}

/**
 * Visits this directory and all its content with the specified [visitor].
 *
 * @param visitor the [FileVisitor] that receives callbacks.
 * @param maxDepth the maximum depth of a directory tree to traverse.
 * @param options the behavior to comply during directory tree traversal. See [FileVisitOption].
 */
public fun Path.visitFileTree(visitor: FileVisitor<Path>, maxDepth: Int, options: Set<FileVisitOption>): Unit {
    Files.walkFileTree(this, options, maxDepth, visitor)
}
