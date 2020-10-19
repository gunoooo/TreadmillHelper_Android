package kr.hs.dgsw.domain.entity.routine

import kr.hs.dgsw.domain.entity.VideoCategory

data class RelatedVideo(
    val idx: Int,
    val source: String,
    val title: String,
    val thumbnail: String,
    val duration: Int,
    val category: VideoCategory
)