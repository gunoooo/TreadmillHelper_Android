package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.ScheduleType

data class ScheduleData(
    val idx: Int,
    val title: String,
    val scheduleType: ScheduleType,
    val partList: List<PartData>,
    val relatedVideoList: List<VideoData>
) {
    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(idx, title, scheduleType, emptyList(), emptyList())

    constructor(title: String, scheduleType: ScheduleType, partList: List<PartData>, relatedVideoList: List<VideoData>):
            this(0, title, scheduleType, partList, relatedVideoList)
}