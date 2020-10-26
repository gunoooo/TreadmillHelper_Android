package kr.hs.dgsw.treadmill_helper.etc.extension

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isYouTubeUrl(): Boolean {
    val reg =
        "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)"
    val pattern: Pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.find()
}