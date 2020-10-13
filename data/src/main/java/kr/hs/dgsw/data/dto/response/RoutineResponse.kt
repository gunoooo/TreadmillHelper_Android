package kr.hs.dgsw.data.dto.response

import kr.hs.dgsw.data.entity.RoutineData

data class RoutineResponse(
    val routines: List<RoutineData>
)