package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {
    @Query("SELECT * FROM schedule_table")
    fun getScheduleList(): Single<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule_table")
    fun getScheduleWithPartList(): Single<List<ScheduleWithPartEntity>>
}