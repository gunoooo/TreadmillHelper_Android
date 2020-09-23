package kr.hs.dgsw.domain.entity

import kr.hs.dgsw.domain.entity.enum.ScheduleType

data class Schedule(
    val idx: Int,
    val title: String,
    val scheduleType: ScheduleType,
    val partList: List<Part>
) {
    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(idx, title, scheduleType, emptyList())
}