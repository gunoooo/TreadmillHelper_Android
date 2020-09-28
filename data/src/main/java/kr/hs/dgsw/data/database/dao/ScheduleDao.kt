package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {
    @Query("SELECT * FROM schedule_table")
    fun getScheduleList(): Single<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule_table")
    fun getScheduleDetailList(): Single<List<ScheduleDetailEntity>>

    @Query("SELECT * FROM schedule_table WHERE idx=:scheduleIdx")
    fun getScheduleDetail(scheduleIdx: Int): Single<ScheduleDetailEntity>

    @Query("DELETE FROM schedule_table WHERE idx=:idx")
    fun deleteByIdx(idx: Int): Completable
}