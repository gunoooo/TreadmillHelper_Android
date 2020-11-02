package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.PartEntity

@Dao
interface PartDao : BaseDao<PartEntity> {
    @Query("SELECT * FROM part_table")
    fun getPartList(): Single<List<PartEntity>>

    @Query("SELECT * FROM part_table WHERE routineIdx=:routineIdx")
    fun getPartListByRoutineIdx(routineIdx: Int): Single<List<PartEntity>>

    @Query("DELETE FROM part_table WHERE idx=:idx")
    fun deleteByIdx(idx: Int): Completable

    @Query("DELETE FROM part_table WHERE routineIdx=:routineIdx")
    fun deleteByRoutineIdx(routineIdx: Int): Completable
}