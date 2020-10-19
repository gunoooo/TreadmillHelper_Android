package kr.hs.dgsw.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDao
import kr.hs.dgsw.data.database.entity.VideoEntity

@Dao
interface VideoDao : BaseDao<VideoEntity> {
    @Query("SELECT * FROM video_table")
    fun getVideoList(): Single<List<VideoEntity>>
}