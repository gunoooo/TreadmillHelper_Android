package kr.hs.dgsw.domain.entity.routine

import kr.hs.dgsw.domain.entity.RoutineType
import kr.hs.dgsw.domain.entity.video.Video
import java.io.Serializable
import kotlin.math.roundToInt

data class Routine(
    val idx: Int,
    val title: String,
    val routineType: RoutineType,
    val partList: List<Part>,
    val relatedVideoList: List<RelatedVideo>
) : Serializable {
    constructor(idx: Int, title: String, routineType: RoutineType, partList: List<Part>):
            this(idx, title, routineType, partList, emptyList())

    constructor(idx: Int, title: String, routineType: RoutineType):
            this(idx, title, routineType, emptyList(), emptyList())

    constructor(title: String, routineType: RoutineType, partList: List<Part>, relatedVideoList: List<RelatedVideo>):
            this(0, title, routineType, partList, relatedVideoList)

    fun getCalorie(): Int =
        partList.sumBy { it.getCalorie() }

    fun getDistance(): Double =
        (partList.sumByDouble { it.getDistance() } * 100).roundToInt() / 100.0

    fun getTime(): Int =
        partList.sumBy { it.time }
}