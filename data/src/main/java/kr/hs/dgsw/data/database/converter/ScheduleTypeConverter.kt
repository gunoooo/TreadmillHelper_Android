package kr.hs.dgsw.data.database.converter

import androidx.room.TypeConverter
import kr.hs.dgsw.domain.model.schedule.ScheduleType

class ScheduleTypeConverter {
    @TypeConverter
    fun toScheduleType(value: String) = enumValueOf<ScheduleType>(value)

    @TypeConverter
    fun fromScheduleType(value: ScheduleType) = value.name
}