package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData

interface ScheduleDataSource {
    fun getScheduleDetailList(): Single<List<ScheduleData>>

    fun getScheduleList(): Single<List<ScheduleData>>

    fun getSchedule(scheduleIdx: Int): Single<ScheduleData>

    fun insertSchedule(scheduleData: ScheduleData): Completable

    fun updateSchedule(scheduleData: ScheduleData): Completable
}