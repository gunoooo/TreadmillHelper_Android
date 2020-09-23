package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDataSource
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.mapper.*
import kr.hs.dgsw.data.network.remote.ScheduleRemote
import kr.hs.dgsw.domain.entity.Part
import kr.hs.dgsw.domain.entity.Schedule
import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(
    override val remote: ScheduleRemote,
    override val cache: ScheduleCache
) : BaseDataSource<ScheduleRemote, ScheduleCache>(), ScheduleDataSource {
    override fun getScheduleWithPartList(): Single<List<ScheduleData>> {
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
                    it.toDataEntity()
                }
            }
    }

    override fun getScheduleList(): Single<List<ScheduleData>> {
        return cache.getScheduleList()
            .onErrorResumeNext {
                remote.getPresetScheduleList()
                    .map { scheduleList ->
                        scheduleList.map { it.toScheduleWithPartEntity() }
                    }
                    .flatMap { scheduleEntityList ->
                        cache.insertScheduleWithPartList(scheduleEntityList)
                            .toSingleDefault(
                                scheduleEntityList.map { it.toDbEntity() }
                            )
                    }
            }
            .map { scheduleList ->
                scheduleList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun getPartList(scheduleIdx: Int): Single<List<PartData>> {
        return cache.getPartList(scheduleIdx).map { partList ->
            partList.map {
                it.toDataEntity()
            }
        }
    }

    override fun insertScheduleList(scheduleDataList: List<ScheduleData>): Completable {
        return cache.insertScheduleList(scheduleDataList.map { it.toDbEntity() })
    }

    override fun insertSchedule(scheduleData: ScheduleData): Completable {
        return cache.insertSchedule(scheduleData.toDbEntity())
    }

    override fun deleteSchedule(scheduleIdx: Int): Completable {
        return cache.deleteSchedule(scheduleIdx)
    }

    override fun deletePart(scheduleIdx: Int): Completable {
        return cache.deletePart(scheduleIdx)
    }

    override fun updateSchedule(scheduleData: ScheduleData): Completable {
        return cache.updateSchedule(
            scheduleData.toDbEntity(),
            scheduleData.partList.map { it.toDbEntity() }
        )
    }
}