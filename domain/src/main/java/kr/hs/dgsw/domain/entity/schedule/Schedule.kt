package kr.hs.dgsw.domain.entity.schedule

import kr.hs.dgsw.domain.entity.ScheduleType
import kr.hs.dgsw.domain.entity.video.Video

data class Schedule(
    val idx: Int,
    val title: String,
    val scheduleType: ScheduleType,
    val partList: List<Part>,
    val relatedVideoList: List<Video>
) {
    constructor(idx: Int, title: String, scheduleType: ScheduleType, partList: List<Part>):
            this(idx, title, scheduleType, partList, emptyList())

    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(idx, title, scheduleType, emptyList(), emptyList())

    constructor(title: String, scheduleType: ScheduleType, partList: List<Part>, relatedVideoList: List<Video>):
            this(0, title, scheduleType, partList, relatedVideoList)
}