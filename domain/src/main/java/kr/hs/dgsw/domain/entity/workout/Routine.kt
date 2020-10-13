package kr.hs.dgsw.domain.entity.workout

import kr.hs.dgsw.domain.entity.RoutineType
import kr.hs.dgsw.domain.entity.video.Video
import java.io.Serializable

data class Routine(
    val idx: Int,
    val title: String,
    val routineType: RoutineType,
    val partList: List<Part>,
    val relatedVideoList: List<Video>
) : Serializable {
    constructor(idx: Int, title: String, routineType: RoutineType, partList: List<Part>):
            this(idx, title, routineType, partList, emptyList())

    constructor(idx: Int, title: String, routineType: RoutineType):
            this(idx, title, routineType, emptyList(), emptyList())

    constructor(title: String, routineType: RoutineType, partList: List<Part>, relatedVideoList: List<Video>):
            this(0, title, routineType, partList, relatedVideoList)

    fun getCalorie(): Int =
        partList.sumBy { it.getCalorie() }

    fun getDistance(): Double =
        partList.sumByDouble { it.getDistance() }
}