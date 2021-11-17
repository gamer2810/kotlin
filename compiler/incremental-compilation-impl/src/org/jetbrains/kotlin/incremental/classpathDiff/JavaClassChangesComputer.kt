/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.classpathDiff

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.resolve.jvm.JvmClassName
import org.jetbrains.kotlin.resolve.sam.SAM_LOOKUP_NAME

/** Computes [ChangeSet] between two lists of [JavaClassSnapshot]s .*/
object JavaClassChangesComputer {

    /**
     * Computes [ChangeSet] between two lists of [JavaClassSnapshot]s.
     *
     * Each list must not contain duplicate classes (having the same [JvmClassName]/[ClassId]).
     */
    fun compute(
        currentJavaClassSnapshots: List<RegularJavaClassSnapshot>,
        previousJavaClassSnapshots: List<RegularJavaClassSnapshot>
    ): ChangeSet {
        val currentClasses: Map<ClassId, RegularJavaClassSnapshot> = currentJavaClassSnapshots.associateBy { it.classId }
        val previousClasses: Map<ClassId, RegularJavaClassSnapshot> = previousJavaClassSnapshots.associateBy { it.classId }

        // Note: Added classes can also impact recompilation.
        // For example, suppose a source file uses `SomeClass` through `*` imports:
        //     import foo.* // foo.SomeClass is added in the second build
        //     import bar.* // bar.SomeClass is present in the first build and unchanged in the second build
        // In the second build, the source file needs to be recompiled as `SomeClass` is ambiguous now (in this example, the recompilation
        // will fail, but recompilation needs to happen).
        val addedClasses = currentClasses.keys - previousClasses.keys
        val removedClasses = previousClasses.keys - currentClasses.keys
        val unchangedOrModifiedClasses = currentClasses.keys - addedClasses

        return ChangeSet.Collector().run {
            addChangedClasses(addedClasses)
            addChangedClasses(removedClasses)
            unchangedOrModifiedClasses.forEach {
                collectClassChanges(currentClasses[it]!!, previousClasses[it]!!, this)
            }
            getChanges()
        }
    }

    /**
     * Collects changes between two [JavaClassSnapshot]s.
     *
     * The two classes must have the same [ClassId].
     */
    private fun collectClassChanges(
        currentClassSnapshot: RegularJavaClassSnapshot,
        previousClassSnapshot: RegularJavaClassSnapshot,
        changes: ChangeSet.Collector
    ) {
        val classId = currentClassSnapshot.classId.also { check(it == previousClassSnapshot.classId) }
        if (currentClassSnapshot.classAbiExcludingMembers.abiHash != previousClassSnapshot.classAbiExcludingMembers.abiHash) {
            changes.addChangedClass(classId)
        } else {
            collectClassMemberChanges(classId, currentClassSnapshot.fieldsAbi, previousClassSnapshot.fieldsAbi, changes)
            collectClassMemberChanges(classId, currentClassSnapshot.methodsAbi, previousClassSnapshot.methodsAbi, changes)
        }
    }

    /** Collects changes between two lists of fields/methods within a class. */
    private fun collectClassMemberChanges(
        classId: ClassId,
        currentMemberSnapshots: List<AbiSnapshot>,
        previousMemberSnapshots: List<AbiSnapshot>,
        changes: ChangeSet.Collector
    ) {
        val currentMemberHashes: Map<Long, AbiSnapshot> = currentMemberSnapshots.associateBy { it.abiHash }
        val previousMemberHashes: Map<Long, AbiSnapshot> = previousMemberSnapshots.associateBy { it.abiHash }

        val addedMembers = currentMemberHashes.keys - previousMemberHashes.keys
        val removedMembers = previousMemberHashes.keys - currentMemberHashes.keys

        // Note:
        //   - Added members can also impact recompilation. For example, suppose a source file calls `foo(1)` where `foo` is defined as:
        //         fun foo(x: Int) { } // Added in the second build
        //         fun foo(x: Any) { } // Present in the first build and unchanged in the second build
        //     In the second build, the source file needs to be recompiled as `foo(1)` will now resolve to `foo(Int)` instead of `foo(Any)`.
        //   - Modified members will appear in both addedMembers and removedMembers.
        //   - Multiple members may have the same name (but never the same signature (name + desc) or ABI hash). It's okay to report the
        //     same name multiple times.
        changes.addChangedClassMembers(classId, addedMembers.map { currentMemberHashes[it]!!.name })
        changes.addChangedClassMembers(classId, removedMembers.map { previousMemberHashes[it]!!.name })

        // TODO: Check whether the condition to add SAM_LOOKUP_NAME below is too broad, and correct it if necessary.
        // Currently, it matches the logic in ChangesCollector.getDirtyData in buildUtil.kt.
        if (addedMembers.isNotEmpty() || removedMembers.isNotEmpty()) {
            changes.addChangedClassMember(classId, SAM_LOOKUP_NAME.asString())
        }
    }
}
