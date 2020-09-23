package kr.hs.dgsw.data.dto.response

import kr.hs.dgsw.data.entity.ScheduleData

data class ScheduleResponse(
    val schedules: List<ScheduleData>
)