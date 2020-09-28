package kr.hs.dgsw.domain.entity.schedule

import kr.hs.dgsw.domain.entity.Color

data class Part(
    val title: String,
    val time: Int,
    val color: Color,
    val speed: Double,
    val incline: Int
)