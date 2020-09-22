package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule

interface ScheduleDataSource {
    fun getScheduleWithPartList(): Single<List<Schedule>>

    fun getScheduleList(): Single<List<Schedule>>

    fun getPartList(scheduleIdx: Int): Single<List<Part>>

    fun insertScheduleList(scheduleList:List<Schedule>): Completable

    fun insertSchedule(schedule: Schedule): Completable

    fun deleteSchedule(scheduleIdx: Int): Completable

    fun deletePart(scheduleIdx: Int): Completable

    fun updateSchedule(schedule: Schedule): Completable
}