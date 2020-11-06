package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import javax.inject.Inject

class PartCacheImpl @Inject constructor(application: Application) :
    BaseCache(application), PartCache {
    private val partDao = database.partDao()

    override fun getPartList(): Single<List<PartEntity>> {
        return partDao.getPartList()
    }

    override fun insertPart(partEntity: PartEntity): Single<PartEntity> {
        return partDao.insertGetIdx(partEntity)
            .map { idx ->
                partEntity.idx = idx.toInt()
                partEntity
            }
    }

    override fun insertPartList(partEntityList: List<PartEntity>): Single<List<PartEntity>> {
        return partDao.insertGetIdx(partEntityList)
            .map { idxList ->
                idxList.forEachIndexed { i, idx ->
                    partEntityList[i].idx = idx.toInt()
                }
                partEntityList
            }
    }

    override fun deletePart(partIdx: Int): Completable {
        return partDao.deleteByIdx(partIdx)
    }

    override fun deletePartByRoutineIdx(routineIdx: Int): Completable {
        return partDao.deleteByRoutineIdx(routineIdx)
    }

    override fun updatePart(partEntity: PartEntity): Completable {
        return partDao.update(partEntity)
    }

    override fun updatePartAll(routineIdx: Int, partEntityList: List<PartEntity>): Completable {
        return partDao.deleteByRoutineIdx(routineIdx)
            .andThen(partDao.insert(partEntityList))
    }
}