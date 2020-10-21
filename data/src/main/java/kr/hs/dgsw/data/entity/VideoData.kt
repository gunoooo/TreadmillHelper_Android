package kr.hs.dgsw.data.entity

data class VideoData(
    val source: String,
    val title: String,
    val thumbnail: String,
    val duration: Int,
    val category: String
)