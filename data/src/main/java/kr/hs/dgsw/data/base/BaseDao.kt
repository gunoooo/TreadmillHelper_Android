package kr.hs.dgsw.data.base

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BaseDao<ET> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ET): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<ET>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetIdx(entity: ET): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetIdx(entity: List<ET>): Single<List<Long>>

    @Update
    fun update(entity: ET): Completable

    @Delete
    fun delete(entity: ET): Completable
}
