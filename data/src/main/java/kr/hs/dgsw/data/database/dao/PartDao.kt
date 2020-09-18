package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.PartEntity

@Dao
interface PartDao : BaseDao<PartEntity> {
    @Query("SELECT * FROM part_table WHERE scheduleIdx=:scheduleIdx")
    fun getPartList(scheduleIdx: Int): Single<List<PartEntity>>

    @Query("DELETE FROM part_table WHERE scheduleIdx=:scheduleIdx")
    fun deleteByScheduleIdx(scheduleIdx: Int): Completable
}