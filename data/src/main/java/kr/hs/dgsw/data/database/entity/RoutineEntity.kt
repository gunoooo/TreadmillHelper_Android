package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_table")
data class RoutineEntity(
    val title: String,
    val routineType: String
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, title: String, routineType: String):
            this(title, routineType) {
        this.idx = idx
    }
}