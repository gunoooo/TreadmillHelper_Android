package kr.hs.dgsw.domain.model.schedule

import kr.hs.dgsw.domain.model.Color

data class Part(
    val idx: Int,
    val scheduleIdx: Int,
    val title: String,
    val time: Int,
    val color: Color,
    val speed: Double,
    val incline: Int
)