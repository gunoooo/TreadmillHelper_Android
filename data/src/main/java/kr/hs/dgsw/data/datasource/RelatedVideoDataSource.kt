package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import kr.hs.dgsw.data.entity.RelatedVideoData

interface RelatedVideoDataSource {
    fun insertRelatedVideo(relatedVideoData: RelatedVideoData): Completable

    fun insertRelatedVideoList(relatedVideoDataList: List<RelatedVideoData>): Completable

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable

    fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable

    fun updateRelatedVideoAll(routineIdx: Int, relatedVideoDataList: List<RelatedVideoData>): Completable
}