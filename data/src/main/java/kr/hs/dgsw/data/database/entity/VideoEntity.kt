package kr.hs.dgsw.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.hs.dgsw.domain.entity.VideoCategory

@Entity(tableName = "video_table")
data class VideoEntity(
    var routineIdx: Int,
    val title: String,
    val thumbnail: String,
    val source: String,
    val duration: Int,
    val category: VideoCategory
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0

    constructor(idx: Int, routineIdx: Int, title: String, thumbnail: String, source: String, duration: Int, category: VideoCategory):
            this(routineIdx, title, thumbnail, source, duration, category) {
        this.idx = idx
    }

    constructor(title: String, thumbnail: String, source: String, duration: Int, category: VideoCategory):
            this(0, title, thumbnail, source, duration, category)
}