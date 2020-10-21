package kr.hs.dgsw.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import kr.hs.dgsw.data.database.dao.PartDao
import kr.hs.dgsw.data.database.dao.RoutineDao
import kr.hs.dgsw.data.database.dao.RelatedVideoDao
import kr.hs.dgsw.data.database.dao.VideoDao
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.database.entity.VideoEntity

@Database(
    entities = [
        RoutineEntity::class,
        PartEntity::class,
        RelatedVideoEntity::class,
        VideoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun routineDao(): RoutineDao
    abstract fun partDao(): PartDao
    abstract fun relatedVideoDao(): RelatedVideoDao
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
