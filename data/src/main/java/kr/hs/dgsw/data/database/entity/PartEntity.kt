package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "part_table",
    foreignKeys = [ForeignKey(
        entity = RoutineEntity::class,
        parentColumns = arrayOf("idx"),
        childColumns = arrayOf("routineIdx"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PartEntity(
    var routineIdx: Int,
    val title: String,
    val time: Int,
    val color: String,
    val speed: Double,
    val incline: Int
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, routineIdx: Int, title: String, time: Int, color: String, speed: Double, incline: Int):
            this(routineIdx, title, time, color, speed, incline) {
        this.idx = idx
    }

    constructor(title: String, time: Int, color: String, speed: Double, incline: Int):
            this(0, title, time, color, speed, incline)
}