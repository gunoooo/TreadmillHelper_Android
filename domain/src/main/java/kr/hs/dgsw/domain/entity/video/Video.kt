package kr.hs.dgsw.domain.entity.video

import kr.hs.dgsw.domain.entity.VideoCategory
import java.io.Serializable

data class Video(
    val title: String,
    val thumbnail: String,
    val source: String,
    val duration: Int,
    val category: VideoCategory
) : Serializable