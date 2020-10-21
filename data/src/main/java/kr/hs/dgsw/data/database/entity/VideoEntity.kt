package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_table")
data class VideoEntity(
    val title: String,
    val thumbnail: String,
    val source: String,
    val duration: Int,
    val category: String
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, title: String, thumbnail: String, source: String, duration: Int, category: String):
            this(title, thumbnail, source, duration, category) {
        this.idx = idx
    }
}