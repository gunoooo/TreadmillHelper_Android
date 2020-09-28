package kr.hs.dgsw.treadmill_helper.etc.extension

fun Int.format(digits: Int) = "%0${digits}d".format(this)