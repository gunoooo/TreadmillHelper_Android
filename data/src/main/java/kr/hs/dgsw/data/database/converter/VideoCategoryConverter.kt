package kr.hs.dgsw.data.database.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.domain.entity.VideoCategory

class VideoCategoryConverter {
    @TypeConverter
    fun toVideoCategory(value: String) = enumValueOf<VideoCategory>(value)

    @TypeConverter
    fun fromVideoCategory(value: VideoCategory) = value.name
}