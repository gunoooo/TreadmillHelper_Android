package kr.hs.dgsw.domain.model.schedule

data class Schedule(
    val idx: Int,
    val title: String,
    val partList: List<Part>
)