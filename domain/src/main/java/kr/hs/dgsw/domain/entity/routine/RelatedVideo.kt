package kr.hs.dgsw.domain.entity.routine

import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import java.io.Serializable

data class RelatedVideo(
    var idx: Int,
    val source: String,
    val title: String,
    val thumbnail: String,
    val duration: Int,
    val category: VideoCategoryEnum
) : Serializable