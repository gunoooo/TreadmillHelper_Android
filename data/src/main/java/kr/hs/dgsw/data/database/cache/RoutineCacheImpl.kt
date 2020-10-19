package kr.hs.dgsw.data.database.cache

import android.app.Application
import androidx.room.EmptyResultSetException
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import javax.inject.Inject

class RoutineCacheImpl @Inject constructor(application: Application) :
    BaseCache(application), RoutineCache {
    private val routineDao = database.routineDao()
    private val partDao = database.partDao()
    private val relatedVideoDao = database.relatedVideoDao()

    override fun getRoutineList(): Single<List<RoutineEntity>> {
        return routineDao.getRoutineList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("routine_table"))
            else
                Single.just(it)
        }
    }

    override fun getRoutineDetailList(): Single<List<RoutineDetailEntity>> {
        return routineDao.getRoutineDetailList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("routine_table"))
            else
                Single.just(it)
        }
    }

    override fun getRoutineDetail(routineIdx: Int): Single<RoutineDetailEntity> {
        return routineDao.getRoutineDetail(routineIdx).onErrorResumeNext {
            if (it is EmptyResultSetException)
                Single.error(TableEmptyException(it))
            else
                Single.error(it)
        }
    }

    override fun insertRoutineDetailList(routineDetailEntityList: List<RoutineDetailEntity>): Completable {
        return routineDao.insertGetIdx(routineDetailEntityList.map { it.routine })
            .flatMapCompletable { idxList ->
                idxList.forEachIndexed { i, idx ->
                    routineDetailEntityList[i].partList
                        .forEach { it.routineIdx = idx.toInt() }
                    routineDetailEntityList[i].relatedVideoList
                        .forEach { it.routineIdx = idx.toInt() }
                }
                partDao.insert(routineDetailEntityList.flatMap { it.partList })
                    .andThen(relatedVideoDao.insert(routineDetailEntityList.flatMap { it.relatedVideoList }))
            }
    }

    override fun insertRoutineDetail(routineDetailEntity: RoutineDetailEntity): Completable {
        return routineDao.insertGetIdx(routineDetailEntity.routine)
            .flatMapCompletable { idx ->
                routineDetailEntity.partList
                    .forEach { it.routineIdx = idx.toInt() }
                routineDetailEntity.relatedVideoList
                    .forEach { it.routineIdx = idx.toInt() }
                partDao.insert(routineDetailEntity.partList)
                    .andThen(relatedVideoDao.insert(routineDetailEntity.relatedVideoList))
            }
    }

    override fun deleteRoutine(routineIdx: Int): Completable {
        return routineDao.deleteByIdx(routineIdx)
    }

    override fun updateRoutine(
        routineEntity: RoutineEntity,
        partEntityList: List<PartEntity>,
        relatedVideoEntityList: List<RelatedVideoEntity>
    ): Completable {
        partEntityList
            .forEach { it.routineIdx = routineEntity.idx }
        relatedVideoEntityList
            .forEach { it.routineIdx = routineEntity.idx }
        return relatedVideoDao.deleteByRoutineIdx(routineEntity.idx)
            .andThen(partDao.deleteByRoutineIdx(routineEntity.idx)
                .andThen(routineDao.deleteByIdx(routineEntity.idx)
                    .andThen(routineDao.insert(routineEntity)
                        .andThen(partDao.insert(partEntityList)
                            .andThen(relatedVideoDao.insert(relatedVideoEntityList))))))
    }
}