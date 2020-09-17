package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class ScheduleEntity(
    val title: String
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}