package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.VideoCategory

data class RelatedVideoData(
    val idx: Int,
    val source: String,
    val title: String,
    val thumbnail: String,
    val duration: Int,
    val category: VideoCategory
) {
    constructor(source: String, title: String, thumbnail: String, duration: Int, category: VideoCategory):
            this(0, source, title, thumbnail, duration, category)
}