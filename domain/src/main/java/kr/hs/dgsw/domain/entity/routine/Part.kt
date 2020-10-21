package kr.hs.dgsw.domain.entity.routine

import kr.hs.dgsw.domain.entity.e.ColorEnum
import java.io.Serializable
import kotlin.math.roundToInt

data class Part(
    val idx: Int,
    val title: String,
    var time: Int,
    val color: ColorEnum,
    val speed: Double,
    val incline: Int
) : Serializable {
    fun getCalorie(): Int {
        val weight = 60
        val mMin = this.speed * 26.8
        val incline = this.incline / 100.0
        val time = this.time / 60.0
        val oxygen = (mMin * 1.8 * incline) + (mMin * 0.1) + 3.5
        val mCal = oxygen * weight / 200.0
        return (mCal * time).roundToInt()
    }

    fun getDistance(): Double {
        val time = this.time / 3600.0
        return (speed * time * 100).roundToInt() / 100.0
    }
}