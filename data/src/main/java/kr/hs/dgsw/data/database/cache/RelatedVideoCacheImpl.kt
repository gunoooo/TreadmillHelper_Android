package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import javax.inject.Inject

class RelatedVideoCacheImpl @Inject constructor(application: Application) :
    BaseCache(application), RelatedVideoCache {
    private val relatedVideoDao = database.relatedVideoDao()

    override fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Completable {
        return relatedVideoDao.insert(relatedVideoEntity)
    }

    override fun insertRelatedVideoList(relatedVideoEntityList: List<RelatedVideoEntity>): Completable {
        return relatedVideoDao.insert(relatedVideoEntityList)
    }

    override fun deleteRelatedVideo(relatedVideoIdx: Int): Completable {
        return relatedVideoDao.deleteByIdx(relatedVideoIdx)
    }

    override fun deleteRelatedVideoByRoutineIdx(routineIdx: Int): Completable {
        return relatedVideoDao.deleteByRoutineIdx(routineIdx)
    }

    override fun updateRelatedVideoAll(
        routineIdx: Int,
        relatedVideoEntityList: List<RelatedVideoEntity>
    ): Completable {
        return relatedVideoDao.deleteByRoutineIdx(routineIdx)
            .andThen(relatedVideoDao.insert(relatedVideoEntityList))
    }
}