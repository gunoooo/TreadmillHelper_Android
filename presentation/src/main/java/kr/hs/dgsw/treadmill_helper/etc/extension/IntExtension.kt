package kr.hs.dgsw.treadmill_helper.etc.extension

fun Int.format(digits: Int) = "%0${digits}d".format(this)

fun Int.toMilliseconds() = this * 1000

fun Int.secToTimeFormat(): String {
    val hours = (this / 60 / 12).format(2)
    val minutes = (this / 60 % 12).format(2)
    val seconds = (this % 60).format(2)
    return if (hours != "00")
        "$hours : $minutes : $seconds"
    else
        "$minutes : $seconds"
}

fun Int.milliToMin() = (this / 1000 / 60 % 12).format(2)

fun Int.milliToSec() = (this / 1000 % 60).format(2)