package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDataSource
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.data.mapper.toModel
import kr.hs.dgsw.data.mapper.toScheduleWithPartEntity
import kr.hs.dgsw.data.network.remote.ScheduleRemote
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule
import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(
    override val remote: ScheduleRemote,
    override val cache: ScheduleCache
) : BaseDataSource<ScheduleRemote, ScheduleCache>(), ScheduleDataSource {
    override fun getScheduleWithPartList(): Single<List<Schedule>> {
        return cache.getScheduleWithPartList()
            .onErrorResumeNext {
                remote.getPresetScheduleList()
                    .map { scheduleList ->
                        scheduleList.map { it.toScheduleWithPartEntity() }
                    }
                    .flatMap { scheduleEntityList ->
                        cache.insertScheduleWithPartList(scheduleEntityList)
                            .toSingleDefault(scheduleEntityList)
                    }
            }
            .map { scheduleList ->
                scheduleList.map {
                    it.toModel()
                }
            }
    }

    override fun getScheduleList(): Single<List<Schedule>> {
        return cache.getScheduleList()
            .onErrorResumeNext {
                remote.getPresetScheduleList()
                    .map { scheduleList ->
                        scheduleList.map { it.toScheduleWithPartEntity() }
                    }
                    .flatMap { scheduleEntityList ->
                        cache.insertScheduleWithPartList(scheduleEntityList)
                            .toSingleDefault(
                                scheduleEntityList.map { it.toEntity() }
                            )
                    }
            }
            .map { scheduleList ->
                scheduleList.map {
                    it.toModel()
                }
            }
    }

    override fun getPartList(scheduleIdx: Int): Single<List<Part>> {
        return cache.getPartList(scheduleIdx).map { partList ->
            partList.map {
                it.toModel()
            }
        }
    }

    override fun insertScheduleList(scheduleList: List<Schedule>): Completable {
        return cache.insertScheduleList(scheduleList.map { it.toEntity() })
    }

    override fun insertSchedule(schedule: Schedule): Completable {
        return cache.insertSchedule(schedule.toEntity())
    }

    override fun deleteSchedule(scheduleIdx: Int): Completable {
        return cache.deleteSchedule(scheduleIdx)
    }

    override fun deletePart(scheduleIdx: Int): Completable {
        return cache.deletePart(scheduleIdx)
    }

    override fun updateSchedule(schedule: Schedule): Completable {
        return cache.updateSchedule(
            schedule.toEntity(),
            schedule.partList.map { it.toEntity() }
        )
    }
}