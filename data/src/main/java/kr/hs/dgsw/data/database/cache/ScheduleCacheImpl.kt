package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import javax.inject.Inject

class ScheduleCacheImpl @Inject constructor(application: Application)
    : BaseCache(application), ScheduleCache {
    private val scheduleDao = database.scheduleDao()
    private val partDao = database.partDao()

    override fun getScheduleList(): Single<List<ScheduleEntity>> {
        return scheduleDao.getScheduleList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("schedule_table"))
            else
                Single.just(it)
        }
    }

    override fun getScheduleWithPartList(): Single<List<ScheduleWithPartEntity>> {
        return scheduleDao.getScheduleWithPartList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("schedule_table"))
            else
                Single.just(it)
        }
    }

    override fun getPartList(scheduleIdx: Int): Single<List<PartEntity>> {
        return partDao.getPartList(scheduleIdx).flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("part_table", scheduleIdx))
            else
                Single.just(it)
        }
    }

    override fun insertScheduleList(scheduleEntityList: List<ScheduleEntity>): Completable {
        return scheduleDao.insert(scheduleEntityList)
    }

    override fun insertScheduleWithPartList(scheduleWithPartEntityList: List<ScheduleWithPartEntity>): Completable {
        return scheduleDao.insert(scheduleWithPartEntityList.map { it.schedule })
                .andThen(partDao.insert(scheduleWithPartEntityList.map { it.partList }.flatten()))
    }

    override fun insertSchedule(scheduleEntity: ScheduleEntity): Completable {
        return scheduleDao.insert(scheduleEntity)
    }

    override fun deleteSchedule(scheduleIdx: Int): Completable {
        return scheduleDao.deleteByIdx(scheduleIdx)
    }

    override fun deletePart(scheduleIdx: Int): Completable {
        return partDao.deleteByScheduleIdx(scheduleIdx)
    }

    override fun updateSchedule(
        scheduleEntity: ScheduleEntity,
        partEntityList: List<PartEntity>
    ): Completable {
        return deletePart(scheduleEntity.idx)
            .andThen(deleteSchedule(scheduleEntity.idx)
                .andThen(insertSchedule(scheduleEntity)
                    .andThen(partDao.insert(partEntityList))))
    }
}