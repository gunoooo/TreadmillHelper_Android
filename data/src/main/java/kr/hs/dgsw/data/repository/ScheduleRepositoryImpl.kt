package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.ScheduleDataSourceImpl
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleDataSource: ScheduleDataSourceImpl
) : ScheduleRepository {
    override fun getPartList(scheduleIdx: Int): Single<List<Part>> {
        return scheduleDataSource.getPartList(scheduleIdx)
    }

    override fun getScheduleList(): Single<List<Schedule>> {
        return scheduleDataSource.getScheduleList()
    }

    override fun insertSchedule(schedule: Schedule): Completable {
        return scheduleDataSource.insertSchedule(schedule)
    }

    override fun updateSchedule(schedule: Schedule): Completable {
        return scheduleDataSource.updateSchedule(schedule)
    }
}