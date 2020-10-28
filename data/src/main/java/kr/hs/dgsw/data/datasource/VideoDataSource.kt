package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.entity.RelatedVideoData
import kr.hs.dgsw.data.entity.VideoData

interface VideoDataSource {
    fun getVideoList(page: Int): Single<List<VideoData>>

    fun getVideoDetail(url: String): Single<VideoData>

    fun insertVideo(videoData: VideoData): Completable

    fun insertRelatedVideo(
        routineIdx: Int,
        relatedVideoData: RelatedVideoData
    ): Completable
}