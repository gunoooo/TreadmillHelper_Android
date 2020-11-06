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

    override fun getRoutineList(): Single<List<RoutineDetailEntity>> {
        return routineDao.getRoutineList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("routine_table"))
            else
                Single.just(it)
        }
    }

    override fun getRoutine(routineIdx: Int): Single<RoutineDetailEntity> {
        return routineDao.getRoutine(routineIdx).onErrorResumeNext {
            if (it is EmptyResultSetException)
                Single.error(TableEmptyException(it))
            else
                Single.error(it)
        }
    }

    override fun insertRoutineList(routineDetailEntityList: List<RoutineDetailEntity>): Single<List<RoutineDetailEntity>> {
        return routineDao.insertGetIdx(routineDetailEntityList.map { it.routine })
            .map { idxList ->
                idxList.forEachIndexed { i, idx ->
                    routineDetailEntityList[i].routine.idx = idx.toInt()
                    routineDetailEntityList[i].partList
                        .forEach { it.routineIdx = idx.toInt() }
                    routineDetailEntityList[i].relatedVideoList
                        .forEach { it.routineIdx = idx.toInt() }
                }
                routineDetailEntityList
            }
    }

    override fun insertRoutine(routineDetailEntity: RoutineDetailEntity): Single<RoutineDetailEntity> {
        return routineDao.insertGetIdx(routineDetailEntity.routine)
            .map { idx ->
                routineDetailEntity.routine.idx = idx.toInt()
                routineDetailEntity.partList
                    .forEach { it.routineIdx = idx.toInt() }
                routineDetailEntity.relatedVideoList
                    .forEach { it.routineIdx = idx.toInt() }
                routineDetailEntity
            }
    }

    override fun deleteRoutine(routineIdx: Int): Completable {
        return routineDao.deleteByIdx(routineIdx)
    }
}