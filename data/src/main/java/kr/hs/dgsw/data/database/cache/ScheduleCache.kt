package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import javax.inject.Inject

open class ScheduleCache @Inject constructor(application: Application) : BaseCache(application) {
    private val scheduleDao = database.scheduleDao()
    private val partDao = database.partDao()

    fun getScheduleList(): Single<List<ScheduleEntity>> {
        return scheduleDao.getScheduleList()
    }

    fun getScheduleWithPartList(): Single<List<ScheduleWithPartEntity>> {
        return scheduleDao.getScheduleWithPartList()
    }

    fun getPartList(scheduleIdx: Int): Single<List<PartEntity>> {
        return partDao.getPartList(scheduleIdx)
    }

    fun insertScheduleList(scheduleEntityList: List<ScheduleEntity>): Completable {
        return scheduleDao.insert(scheduleEntityList)
    }

    fun insertScheduleWithPartList(scheduleWithPartEntityList: List<ScheduleWithPartEntity>): Completable {
        return scheduleDao.insert(scheduleWithPartEntityList.map { it.schedule })
                .andThen(partDao.insert(scheduleWithPartEntityList.map { it.partList }.flatten()))
    }

    fun insertSchedule(scheduleEntity: ScheduleEntity): Completable {
        return scheduleDao.insert(scheduleEntity)
    }

    fun updateSchedule(scheduleEntity: ScheduleEntity,
                       partEntityList: List<PartEntity> = ArrayList()): Completable {
        return scheduleDao.update(scheduleEntity)
                .andThen(partDao.deleteByScheduleIdx(scheduleEntity.idx)
                    .andThen(partDao.insert(partEntityList)))
    }
}