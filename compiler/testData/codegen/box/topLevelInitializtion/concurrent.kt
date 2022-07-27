// TARGET_BACKEND: NATIVE

// FILE: 1.kt

val O = if (true) "O" else "F" // to avoid const init
val K = if (true) "K" else "A" // to avoid const init

// FILE: main.kt

import kotlin.native.concurrent.*

val sem = AtomicInt(0)

fun box() : String {
    val w1 = Worker.start()
    val w2 = Worker.start()
    val f1 = w1.execute(
        mode = TransferMode.SAFE,
        { },
        {
            while (sem.value != 1) {}
            O
        }
    )
    val f2 = w2.execute(
        mode = TransferMode.SAFE,
        { },
        {
            while (sem.value != 1) {}
            K
        }
    )
    sem.value = 1
    val result = f1.result + f2.result
    w1.requestTermination().result
    w2.requestTermination().result
    return result
}