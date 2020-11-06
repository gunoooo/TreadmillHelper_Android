package kr.hs.dgsw.data.entity

data class RoutineData(
    val idx: Int,
    val title: String,
    val routineType: String,
    var partList: List<PartData>,
    var relatedVideoList: List<RelatedVideoData>
) {
    constructor(idx: Int, title: String, routineType: String):
            this(idx, title, routineType, emptyList(), emptyList())

    constructor(title: String, routineType: String, partList: List<PartData>, relatedVideoList: List<RelatedVideoData>):
            this(0, title, routineType, partList, relatedVideoList)
}