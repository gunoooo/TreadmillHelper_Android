package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.domain.model.schedule.ScheduleType

@Entity(tableName = "schedule_table")
data class ScheduleEntity(
    val title: String,
    val scheduleType: ScheduleType
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, title: String, scheduleType: ScheduleType):
            this(title, scheduleType) {
        this.idx = idx
    }
}