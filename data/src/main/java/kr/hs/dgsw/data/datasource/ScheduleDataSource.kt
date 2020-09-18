package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDataSource
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.mapper.PartMapper
import kr.hs.dgsw.data.mapper.ScheduleMapper
import kr.hs.dgsw.data.network.remote.ScheduleRemote
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule
import javax.inject.Inject

open class ScheduleDataSource @Inject constructor(
    override val remote: ScheduleRemote,
    override val cache: ScheduleCache
) : BaseDataSource<ScheduleRemote, ScheduleCache>() {
    fun getScheduleList(): Single<List<Schedule>> {
        return cache.getScheduleWithPartList()
            .onErrorResumeNext {
                remote.getPresetScheduleList()
                    .map { scheduleList ->
                        scheduleList.map { ScheduleMapper.mapToWithPartEntity(it) }
                    }
                    .flatMap { scheduleEntityList ->
                        cache.insertScheduleWithPartList(scheduleEntityList)
                            .toSingleDefault(scheduleEntityList)
                    }
            }
            .map { scheduleList ->
                scheduleList.map {
                    ScheduleMapper.mapToModel(it)
                }
            }
    }

    fun getPartList(scheduleIdx: Int): Single<List<Part>> {
        return cache.getPartList(scheduleIdx).map { partList ->
            partList.map {
                PartMapper.mapToModel(it)
            }
        }
    }

    fun insertSchedule(schedule: Schedule): Completable {
        return cache.insertSchedule(ScheduleMapper.mapToEntity(schedule))
    }

    fun updateSchedule(schedule: Schedule): Completable {
        return cache.updateSchedule(
            ScheduleMapper.mapToEntity(schedule),
            schedule.partList.map { PartMapper.mapToEntity(it) }
        )
    }
}