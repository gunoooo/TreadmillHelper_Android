package kr.hs.dgsw.data.database.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.domain.entity.RoutineType

class RoutineTypeConverter {
    @TypeConverter
    fun toRoutineType(value: String) = enumValueOf<RoutineType>(value)

    @TypeConverter
    fun fromRoutineType(value: RoutineType) = value.name
}