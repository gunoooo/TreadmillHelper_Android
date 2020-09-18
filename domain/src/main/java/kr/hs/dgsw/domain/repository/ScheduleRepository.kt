package kr.hs.dgsw.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule

interface ScheduleRepository {
    fun getPartList(scheduleIdx: Int): Single<List<Part>>
    fun getScheduleList(): Single<List<Schedule>>
    fun insertSchedule(schedule: Schedule): Completable
    fun updateSchedule(schedule: Schedule): Completable
}