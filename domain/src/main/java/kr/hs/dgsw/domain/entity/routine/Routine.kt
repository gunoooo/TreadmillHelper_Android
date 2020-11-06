package kr.hs.dgsw.domain.entity.routine

import kr.hs.dgsw.domain.entity.e.RoutineTypeEnum
import java.io.Serializable
import kotlin.math.roundToInt

data class Routine(
    val idx: Int,
    val title: String,
    val routineType: RoutineTypeEnum,
    var partList: List<Part>,
    var relatedVideoList: List<RelatedVideo>
) : Serializable {
    constructor(idx: Int, title: String, routineType: RoutineTypeEnum, partList: List<Part>):
            this(idx, title, routineType, partList, emptyList())

    constructor(idx: Int, title: String, routineType: RoutineTypeEnum):
            this(idx, title, routineType, emptyList(), emptyList())

    constructor(title: String, routineType: RoutineTypeEnum, partList: List<Part>, relatedVideoList: List<RelatedVideo>):
            this(0, title, routineType, partList, relatedVideoList)

    fun getCalorie(): Int =
        partList.sumBy { it.getCalorie() }

    fun getDistance(): Double =
        (partList.sumByDouble { it.getDistance() } * 100).roundToInt() / 100.0

    fun getTime(): Int =
        partList.sumBy { it.time }
}