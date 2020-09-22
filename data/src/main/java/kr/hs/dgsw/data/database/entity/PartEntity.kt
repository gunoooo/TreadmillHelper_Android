package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kr.hs.dgsw.data.database.converter.ColorConverter
import kr.hs.dgsw.domain.model.Color

@Entity(tableName = "part_table")
data class PartEntity(
    val scheduleIdx: Int,
    val title: String,
    val time: Int,
    val color: Color,
    val speed: Double,
    val incline: Int
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, scheduleIdx: Int, title: String, time: Int, color: Color, speed: Double, incline: Int):
            this(scheduleIdx, title, time, color, speed, incline) {
        this.idx = idx
    }
}