package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.VideoCategory

data class VideoData(
    val source: String,
    val title: String,
    val thumbnail: String,
    val duration: Int,
    val category: VideoCategory
)