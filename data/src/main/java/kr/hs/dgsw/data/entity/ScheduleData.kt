package kr.hs.dgsw.data.entity

import kr.hs.dgsw.domain.entity.enum.ScheduleType

data class ScheduleData(
    val idx: Int,
    val title: String,
    val scheduleType: ScheduleType,
    val partList: List<PartData>
) {
    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(idx, title, scheduleType, emptyList())
}