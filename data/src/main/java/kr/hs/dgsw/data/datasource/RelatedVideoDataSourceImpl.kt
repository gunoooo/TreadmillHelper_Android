package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import kr.hs.dgsw.data.database.cache.RelatedVideoCache
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.entity.RelatedVideoData
import kr.hs.dgsw.data.mapper.toDbEntity
import javax.inject.Inject

class RelatedVideoDataSourceImpl @Inject constructor(
    private val cache: RelatedVideoCache
) : RelatedVideoDataSource {
    override fun insertRelatedVideo(relatedVideoData: RelatedVideoData): Completable {
        return cache.insertRelatedVideo(relatedVideoData.toDbEntity())
    }

    override fun insertRelatedVideoList(relatedVideoDataList: List<RelatedVideoData>): Completable {
        return cache.insertRelatedVideoList(relatedVideoDataList.map { it.toDbEntity() })
    }

    override fun deleteRelatedVideo(relatedVideoIdx: Int): Completable {
        return cache.deleteRelatedVideo(relatedVideoIdx)
    }

    override fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable {
        return cache.deleteRelatedVideoByRoutineIdx(routineIdx)
    }

    override fun updateRelatedVideoAll(
        routineIdx: Int,
        relatedVideoDataList: List<RelatedVideoData>
    ): Completable {
        return cache.updateRelatedVideoAll(
            routineIdx,
            relatedVideoDataList.map { it.toDbEntity() }
        )
    }
}