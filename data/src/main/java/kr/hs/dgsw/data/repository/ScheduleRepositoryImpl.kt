package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.ScheduleDataSource
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.domain.entity.schedule.Schedule
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleDataSource: ScheduleDataSource
) : ScheduleRepository {
    override fun getSchedule(scheduleIdx: Int): Single<Schedule> {
        return scheduleDataSource.getSchedule(scheduleIdx)
            .map { it.toEntity() }
    }

    override fun getScheduleList(): Single<List<Schedule>> {
        return scheduleDataSource.getScheduleList()
            .map { scheduleDataList -> scheduleDataList.map { it.toEntity() } }
    }

    override fun insertSchedule(schedule: Schedule): Completable {
        return scheduleDataSource.insertSchedule(schedule.toDataEntity())
    }

    override fun updateSchedule(schedule: Schedule): Completable {
        return scheduleDataSource.updateSchedule(schedule.toDataEntity())
    }
}