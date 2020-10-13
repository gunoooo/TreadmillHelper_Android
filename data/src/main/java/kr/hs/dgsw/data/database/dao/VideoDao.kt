package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.VideoEntity

@Dao
interface VideoDao : BaseDao<VideoEntity> {
    @Query("SELECT * FROM video_table WHERE routineIdx=:routineIdx")
    fun getVideoList(routineIdx: Int): Single<List<VideoEntity>>

    @Query("DELETE FROM video_table WHERE routineIdx=:routineIdx")
    fun deleteByRoutineIdx(routineIdx: Int): Completable
}