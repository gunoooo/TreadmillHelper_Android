package kr.hs.dgsw.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RoutineDetailEntity(
    @Embedded
    val routine: RoutineEntity,
    @Relation(parentColumn = "idx", entityColumn = "routineIdx")
    val partList: List<PartEntity>,
    @Relation(parentColumn = "idx", entityColumn = "routineIdx")
    val relatedVideoList: List<VideoEntity>
)