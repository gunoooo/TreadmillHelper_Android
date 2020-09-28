package kr.hs.dgsw.data.dto.response

import kr.hs.dgsw.data.entity.VideoData

data class VideoResponse(
    val videos: List<VideoData>
)