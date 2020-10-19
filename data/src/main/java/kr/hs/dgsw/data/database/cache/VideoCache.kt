package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.database.entity.VideoEntity

interface VideoCache {
    fun getVideoList(): Single<List<VideoEntity>>

    fun insertVideo(videoEntity: VideoEntity): Completable

    fun insertVideoList(videoEntityList: List<VideoEntity>): Completable

    fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Completable

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable
}