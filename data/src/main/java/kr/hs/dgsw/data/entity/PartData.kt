package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.Color

data class PartData(
    val idx: Int,
    val title: String,
    val time: Int,
    val color: Color,
    val speed: Double,
    val incline: Int
) {
    constructor(title: String, time: Int, color: Color, speed: Double, incline: Int):
            this(0, title, time, color, speed, incline)
}