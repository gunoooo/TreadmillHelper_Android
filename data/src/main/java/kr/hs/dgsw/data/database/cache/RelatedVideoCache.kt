package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity

interface RelatedVideoCache {
    fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Single<RelatedVideoEntity>

    fun insertRelatedVideoList(relatedVideoEntityList: List<RelatedVideoEntity>): Single<List<RelatedVideoEntity>>

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable

    fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable

    fun updateRelatedVideoAll(routineIdx: Int, relatedVideoEntityList: List<RelatedVideoEntity>): Completable
}