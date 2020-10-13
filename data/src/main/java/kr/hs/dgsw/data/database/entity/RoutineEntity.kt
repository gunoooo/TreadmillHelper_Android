package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.domain.entity.RoutineType

@Entity(tableName = "routine_table")
data class RoutineEntity(
    val title: String,
    val routineType: RoutineType
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, title: String, routineType: RoutineType):
            this(title, routineType) {
        this.idx = idx
    }
}