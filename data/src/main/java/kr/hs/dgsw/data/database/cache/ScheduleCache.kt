package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity
import kr.hs.dgsw.data.database.entity.VideoEntity

interface ScheduleCache {
    fun getScheduleList(): Single<List<ScheduleEntity>>

    fun getScheduleDetailList(): Single<List<ScheduleDetailEntity>>

    fun getScheduleDetail(scheduleIdx: Int): Single<ScheduleDetailEntity>

    fun insertScheduleDetailList(scheduleDetailEntityList: List<ScheduleDetailEntity>): Completable

    fun insertScheduleDetail(scheduleDetailEntity: ScheduleDetailEntity): Completable

    fun deleteSchedule(scheduleIdx: Int): Completable

    fun updateSchedule(
        scheduleEntity: ScheduleEntity,
        partEntityList: List<PartEntity> = emptyList(),
        videoEntityList: List<VideoEntity> = emptyList()
    ): Completable
}