package kr.hs.dgsw.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduleWithPartEntity(
    @Embedded
    val schedule: ScheduleEntity,
    @Relation(parentColumn = "idx", entityColumn = "scheduleIdx")
    val partList: List<PartEntity>
)