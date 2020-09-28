package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.VideoCategory

data class VideoData(
    val idx: Int,
    val title: String,
    val thumbnail: String,
    val source: String,
    val duration: Int,
    val category: VideoCategory
) {
    constructor(title: String, thumbnail: String, source: String, duration: Int, category: VideoCategory):
            this(0, title, thumbnail, source, duration, category)
}