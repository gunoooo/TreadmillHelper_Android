package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.enum.Color

data class PartData(
    val idx: Int,
    val scheduleIdx: Int,
    val title: String,
    val time: Int,
    val color: Color,
    val speed: Double,
    val incline: Int
)