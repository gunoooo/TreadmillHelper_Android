package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity

interface RelatedVideoCache {
    fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Completable

    fun insertRelatedVideoList(relatedVideoEntityList: List<RelatedVideoEntity>): Completable

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable

    fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable

    fun updateRelatedVideoAll(routineIdx: Int, relatedVideoEntityList: List<RelatedVideoEntity>): Completable
}