package kr.hs.dgsw.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import kr.hs.dgsw.data.database.converter.ColorConverter
import kr.hs.dgsw.data.database.dao.PartDao
import kr.hs.dgsw.data.database.dao.ScheduleDao
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class, PartEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(ColorConverter::class)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao
    abstract fun partDao(): PartDao

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
