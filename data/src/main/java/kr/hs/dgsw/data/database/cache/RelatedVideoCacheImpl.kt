package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import javax.inject.Inject

class RelatedVideoCacheImpl @Inject constructor(application: Application) :
    BaseCache(application), RelatedVideoCache {
    private val relatedVideoDao = database.relatedVideoDao()

    override fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Single<RelatedVideoEntity> {
        return relatedVideoDao.insertGetIdx(relatedVideoEntity)
            .map { idx ->
                relatedVideoEntity.idx = idx.toInt()
                relatedVideoEntity
            }

    }

    override fun insertRelatedVideoList(relatedVideoEntityList: List<RelatedVideoEntity>): Single<List<RelatedVideoEntity>> {
        return relatedVideoDao.insertGetIdx(relatedVideoEntityList)
            .map { idxList ->
                idxList.forEachIndexed { i, idx ->
                    relatedVideoEntityList[i].idx = idx.toInt()
                }
                relatedVideoEntityList
            }
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