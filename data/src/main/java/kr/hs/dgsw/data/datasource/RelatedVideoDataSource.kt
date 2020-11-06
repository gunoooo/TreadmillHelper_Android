package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.RelatedVideoData
import kr.hs.dgsw.domain.entity.routine.RelatedVideo

interface RelatedVideoDataSource {
    fun insertRelatedVideo(relatedVideoData: RelatedVideoData): Single<RelatedVideoData>

    fun insertRelatedVideoList(relatedVideoDataList: List<RelatedVideoData>): Single<List<RelatedVideoData>>

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable

    fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable

    fun updateRelatedVideoAll(routineIdx: Int, relatedVideoDataList: List<RelatedVideoData>): Completable
}