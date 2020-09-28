package kr.hs.dgsw.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import kr.hs.dgsw.data.database.converter.ColorConverter
import kr.hs.dgsw.data.database.converter.ScheduleTypeConverter
import kr.hs.dgsw.data.database.converter.VideoCategoryConverter
import kr.hs.dgsw.data.database.dao.PartDao
import kr.hs.dgsw.data.database.dao.ScheduleDao
import kr.hs.dgsw.data.database.dao.VideoDao
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.VideoEntity

@Database(
    entities = [
        ScheduleEntity::class,
        PartEntity::class,
        VideoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ColorConverter::class,
    ScheduleTypeConverter::class,
    VideoCategoryConverter::class
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao
    abstract fun partDao(): PartDao
    abstract fun videoDao(): VideoDao

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "treadmill_helper_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}
