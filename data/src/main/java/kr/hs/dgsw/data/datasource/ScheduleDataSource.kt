package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.domain.entity.Part
import kr.hs.dgsw.domain.entity.Schedule

interface ScheduleDataSource {
    fun getScheduleWithPartList(): Single<List<ScheduleData>>

    fun getScheduleList(): Single<List<ScheduleData>>

    fun getPartList(scheduleIdx: Int): Single<List<PartData>>

    fun insertScheduleList(scheduleDataList: List<ScheduleData>): Completable

    fun insertSchedule(scheduleData: ScheduleData): Completable

    fun deleteSchedule(scheduleIdx: Int): Completable

    fun deletePart(scheduleIdx: Int): Completable

    fun updateSchedule(scheduleData: ScheduleData): Completable
}