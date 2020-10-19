package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity

@Dao
interface RelatedVideoDao : BaseDao<RelatedVideoEntity> {
    @Query("SELECT * FROM related_video_table WHERE routineIdx=:routineIdx")
    fun getRelatedVideoList(routineIdx: Int): Single<List<RelatedVideoEntity>>

    @Query("DELETE FROM related_video_table WHERE routineIdx=:routineIdx")
    fun deleteByRoutineIdx(routineIdx: Int): Completable

    @Query("DELETE FROM related_video_table WHERE idx=:idx")
    fun deleteByIdx(idx: Int): Completable
}