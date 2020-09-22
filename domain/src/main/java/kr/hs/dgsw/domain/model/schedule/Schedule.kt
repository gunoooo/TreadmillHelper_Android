package kr.hs.dgsw.domain.model.schedule

data class Schedule(
    val idx: Int,
    val title: String,
    val scheduleType: ScheduleType,
    val partList: List<Part>
) {
    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(idx, title, scheduleType, emptyList())
}