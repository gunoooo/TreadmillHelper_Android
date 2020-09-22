package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity

interface ScheduleCache {
    fun getScheduleList(): Single<List<ScheduleEntity>>

    fun getScheduleWithPartList(): Single<List<ScheduleWithPartEntity>>

    fun getPartList(scheduleIdx: Int): Single<List<PartEntity>>

    fun insertScheduleList(scheduleEntityList: List<ScheduleEntity>): Completable

    fun insertScheduleWithPartList(scheduleWithPartEntityList: List<ScheduleWithPartEntity>): Completable

    fun insertSchedule(scheduleEntity: ScheduleEntity): Completable

    fun deleteSchedule(scheduleIdx: Int): Completable

    fun deletePart(scheduleIdx: Int): Completable

    fun updateSchedule(
        scheduleEntity: ScheduleEntity,
        partEntityList: List<PartEntity> = emptyList()
    ): Completable
}