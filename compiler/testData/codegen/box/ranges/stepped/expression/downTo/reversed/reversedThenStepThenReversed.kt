// Auto-generated by GenerateSteppedRangesCodegenTestData. Do not edit!
// DONT_TARGET_EXACT_BACKEND: WASM
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
import kotlin.test.*

fun box(): String {
    val intList = mutableListOf<Int>()
    val intProgression = 8 downTo 1
    for (i in (intProgression.reversed() step 2).reversed()) {
        intList += i
    }
    assertEquals(listOf(7, 5, 3, 1), intList)

    val longList = mutableListOf<Long>()
    val longProgression = 8L downTo 1L
    for (i in (longProgression.reversed() step 2L).reversed()) {
        longList += i
    }
    assertEquals(listOf(7L, 5L, 3L, 1L), longList)

    val charList = mutableListOf<Char>()
    val charProgression = 'h' downTo 'a'
    for (i in (charProgression.reversed() step 2).reversed()) {
        charList += i
    }
    assertEquals(listOf('g', 'e', 'c', 'a'), charList)

    return "OK"
}