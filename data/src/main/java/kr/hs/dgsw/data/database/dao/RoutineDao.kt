package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity

@Dao
interface RoutineDao : BaseDao<RoutineEntity> {
    @Query("SELECT * FROM routine_table")
    fun getRoutineList(): Single<List<RoutineDetailEntity>>

    @Query("SELECT * FROM routine_table WHERE idx=:routineIdx")
    fun getRoutine(routineIdx: Int): Single<RoutineDetailEntity>

    @Query("DELETE FROM routine_table WHERE idx=:idx")
    fun deleteByIdx(idx: Int): Completable
}