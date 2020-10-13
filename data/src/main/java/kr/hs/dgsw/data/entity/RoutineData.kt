package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.RoutineType

data class RoutineData(
    val idx: Int,
    val title: String,
    val routineType: RoutineType,
    val partList: List<PartData>,
    val relatedVideoList: List<VideoData>
) {
    constructor(idx: Int, title: String, routineType: RoutineType):
            this(idx, title, routineType, emptyList(), emptyList())

    constructor(title: String, routineType: RoutineType, partList: List<PartData>, relatedVideoList: List<VideoData>):
            this(0, title, routineType, partList, relatedVideoList)
}