package kr.hs.dgsw.treadmill_helper.etc.extension

import android.content.res.Resources
import android.util.TypedValue




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

fun Int.milliToMin() = (this / 1000 / 60).format(2)

fun Int.milliToSec() = (this / 1000 % 60).format(2)

fun Int.dpToPx() =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()