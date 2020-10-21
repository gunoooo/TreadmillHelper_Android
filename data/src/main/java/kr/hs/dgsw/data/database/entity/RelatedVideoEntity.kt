package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "related_video_table",
    foreignKeys = [ForeignKey(
        entity = RoutineEntity::class,
        parentColumns = arrayOf("idx"),
        childColumns = arrayOf("routineIdx"),
        onDelete = ForeignKey.CASCADE
    )])
data class RelatedVideoEntity(
    var routineIdx: Int,
    val title: String,
    val thumbnail: String,
    val source: String,
    val duration: Int,
    val category: String
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, routineIdx: Int, title: String, thumbnail: String, source: String, duration: Int, category: String):
            this(routineIdx, title, thumbnail, source, duration, category) {
        this.idx = idx
    }

    constructor(title: String, thumbnail: String, source: String, duration: Int, category: String):
            this(0, title, thumbnail, source, duration, category)
}