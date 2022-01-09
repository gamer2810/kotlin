// LANGUAGE: -JvmInlineValueClasses, +GenericInlineClassParameter, +SealedInlineClasses
// IGNORE_BACKEND: JVM

sealed inline class IC {
    open val str: String; get() = "FAIL 1"
}

inline class ICString(val s: String): IC() {
    override val str: String; get() = s
}

object ICO: IC() {
    override val str: String; get() = "K"
}

fun toString(ic: IC): String = ic.str

fun box() = toString(ICString("O")) + toString(ICO)