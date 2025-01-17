/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
package kotlin.native

import kotlin.native.concurrent.InvalidMutabilityException
import kotlin.native.internal.ExportForCppRuntime
import kotlin.native.internal.GCUnsafeCall
import kotlin.native.internal.UnhandledExceptionHookHolder
import kotlin.native.internal.runUnhandledExceptionHook
import kotlin.native.internal.ReportUnhandledException

/**
 * Initializes Kotlin runtime for the current thread, if not inited already.
 */
@GCUnsafeCall("Kotlin_initRuntimeIfNeededFromKotlin")
external public fun initRuntimeIfNeeded(): Unit

/**
 * Deinitializes Kotlin runtime for the current thread, if was inited.
 * Cannot be called from Kotlin frames holding references, thus deprecated.
 */
@GCUnsafeCall("Kotlin_deinitRuntimeIfNeeded")
@Deprecated("Deinit runtime can not be called from Kotlin", level = DeprecationLevel.ERROR)
external public fun deinitRuntimeIfNeeded(): Unit

/**
 * Exception thrown when top level variable is accessed from incorrect execution context.
 */
@FreezingIsDeprecated
public class IncorrectDereferenceException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)
}

/**
 * Exception thrown when there was an error during file initalization.
 */
@ExperimentalStdlibApi
public class FileFailedToInitializeException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)
}

/**
 * Typealias describing custom exception reporting hook.
 */
public typealias ReportUnhandledExceptionHook = Function1<Throwable, Unit>

/**
 * Install custom unhandled exception hook. Returns old hook, or null if it was not specified.
 * Hook is invoked whenever there's uncaught exception reaching boundaries of the Kotlin world,
 * i.e. top level main(), or when Objective-C to Kotlin call not marked with @Throws throws an exception.
 * Hook must be a frozen lambda, so that it could be called from any thread/worker.
 */
@OptIn(FreezingIsDeprecated::class)
public fun setUnhandledExceptionHook(hook: ReportUnhandledExceptionHook): ReportUnhandledExceptionHook? {
    try {
        return UnhandledExceptionHookHolder.hook.swap(hook)
    } catch (e: InvalidMutabilityException) {
        throw InvalidMutabilityException("Unhandled exception hook must be frozen")
    }
}

/**
 * Returns a user-defined uncaught exception handler set by [setUnhandledExceptionHook] or `null` if no user-defined handlers were set.
 */
@ExperimentalStdlibApi
@SinceKotlin("1.6")
@OptIn(FreezingIsDeprecated::class)
public fun getUnhandledExceptionHook(): ReportUnhandledExceptionHook? {
    return UnhandledExceptionHookHolder.hook.value
}

/**
 * Performs the default processing of unhandled exception.
 *
 * If user-defined hook set by [setUnhandledExceptionHook] is present, calls it and returns.
 * If the hook is not present, calls [terminateWithUnhandledException] with [throwable].
 * If the hook fails with exception, calls [terminateWithUnhandledException] with exception from the hook.
 */
@ExperimentalStdlibApi
@SinceKotlin("1.6")
@GCUnsafeCall("Kotlin_processUnhandledException")
public external fun processUnhandledException(throwable: Throwable): Unit

/*
 * Terminates the program with the given [throwable] as an unhandled exception.
 * User-defined hooks installed with [setUnhandledExceptionHook] are not invoked.
 *
 * `terminateWithUnhandledException` can be used to emulate an abrupt termination of the application with an uncaught exception.
 */
@ExperimentalStdlibApi
@SinceKotlin("1.6")
@GCUnsafeCall("Kotlin_terminateWithUnhandledException")
public external fun terminateWithUnhandledException(throwable: Throwable): Nothing

/**
 * Compute stable wrt potential object relocations by the memory manager identity hash code.
 * @return 0 for `null` object, identity hash code otherwise.
 */
@GCUnsafeCall("Kotlin_Any_hashCode")
public external fun Any?.identityHashCode(): Int
