// IGNORE_BACKEND_FIR: JVM_IR
// FIR status: the test passes but LambdaMetafactory is used, so bytecode text check fails.
// In this case FIR behavior is fine but it works because of a hack in

// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8
// SAM_CONVERSIONS: INDY

// CHECK_BYTECODE_TEXT
// JVM_IR_TEMPLATES
// 0 java/lang/invoke/LambdaMetafactory
// TODO: restore indy for SAM types with contravariant projections. See KT-52428 for more info.

fun interface Cmp<T> {
    fun compare(a: T, b: T): Int
}

fun <T> foo(comparator: Cmp<in T>, a: T, b: T) = comparator.compare(a, b)

fun bar(x: Int, y: Int) = foo({ a, b -> a - b}, x, y)

fun box(): String {
    val t = bar(42, 117)
    if (t != -75)
        return "Failed: t=$t"
    return "OK"
}
