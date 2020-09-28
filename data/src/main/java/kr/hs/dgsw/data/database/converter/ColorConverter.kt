package kr.hs.dgsw.data.database.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.domain.entity.Color

class ColorConverter {
    @TypeConverter
    fun toColor(value: String) = enumValueOf<Color>(value)

    @TypeConverter
    fun fromColor(value: Color) = value.name
}