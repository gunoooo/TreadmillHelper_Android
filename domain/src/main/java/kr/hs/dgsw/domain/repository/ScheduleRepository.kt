package kr.hs.dgsw.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.domain.entity.schedule.Schedule

interface ScheduleRepository {
    fun getSchedule(scheduleIdx: Int): Single<Schedule>

    fun getScheduleList(): Single<List<Schedule>>

    fun insertSchedule(schedule: Schedule): Completable

    fun updateSchedule(schedule: Schedule): Completable
}