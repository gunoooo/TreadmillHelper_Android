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
    val speed: Int,
    val incline: Int
) {

    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}