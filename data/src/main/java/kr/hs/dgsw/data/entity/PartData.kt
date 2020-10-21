package kr.hs.dgsw.data.entity

data class PartData(
    val idx: Int,
    val title: String,
    val time: Int,
    val color: String,
    val speed: Double,
    val incline: Int
) {
    constructor(title: String, time: Int, color: String, speed: Double, incline: Int):
            this(0, title, time, color, speed, incline)
}