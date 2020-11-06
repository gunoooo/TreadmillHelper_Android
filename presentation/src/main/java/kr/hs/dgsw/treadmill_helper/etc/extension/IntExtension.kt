package kr.hs.dgsw.treadmill_helper.etc.extension

import android.content.res.Resources
import android.util.TypedValue
import java.util.*

fun Int.format(digits: Int) = "%0${digits}d".format(this)

fun Int.toMilliseconds() = this * 1000

fun Int.secToTimeFormat(): String {
    val hours = (this / 60 / 60).format(2)
    val minutes = (this / 60 % 60).format(2)
    val seconds = (this % 60).format(2)
    return if (hours != "00")
        "$hours : $minutes : $seconds"
    else
        "$minutes : $seconds"
}

fun Int.milliToTimeFormat(): String {
    val hours = (this / 1000 / 60 / 60).format(2)
    val minutes = (this / 1000 / 60 % 60).format(2)
    val seconds = (this / 1000 % 60).format(2)
    return if (hours != "00")
        "$hours : $minutes : $seconds"
    else
        "$minutes : $seconds"
}

fun Int.secToSec() = (this % 60 % 60)

fun Int.secToMin() = (this / 60 % 60)

fun Int.secToHour() = (this / 60 / 60)

fun Int.dpToPx() =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

fun Int.toArgbString(): String =
    "#${alpha.toStringComponent()}${red.toStringComponent()}${green.toStringComponent()}${blue.toStringComponent()}".toUpperCase(Locale.getDefault())

private fun Int.toStringComponent(): String =
    this.toString(16).let { if (it.length == 1) "0${it}" else it }

inline val Int.alpha: Int
    get() = (this shr 24) and 0xFF

inline val Int.red: Int
    get() = (this shr 16) and 0xFF

inline val Int.green: Int
    get() = (this shr 8) and 0xFF

inline val Int.blue: Int
    get() = this and 0xFF