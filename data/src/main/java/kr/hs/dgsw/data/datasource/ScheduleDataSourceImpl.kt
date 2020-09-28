package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toDbEntity
import kr.hs.dgsw.data.mapper.toScheduleDetailEntity
import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(
    private val cache: ScheduleCache
) : ScheduleDataSource {
    override fun getScheduleDetailList(): Single<List<ScheduleData>> {
        return cache.getScheduleDetailList()
            .onErrorResumeNext {
                cache.insertScheduleDetailList(Object.presetScheduleList
                    .map { it.toScheduleDetailEntity() })
                    .andThen(cache.getScheduleDetailList())
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
                cache.insertScheduleDetailList(Object.presetScheduleList
                    .map { it.toScheduleDetailEntity() })
                    .andThen(cache.getScheduleList())
            }
            .map { scheduleList ->
                scheduleList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun getSchedule(scheduleIdx: Int): Single<ScheduleData> {
        return cache.getScheduleDetail(scheduleIdx)
            .onErrorResumeNext {
                cache.insertScheduleDetailList(Object.presetScheduleList
                    .map { it.toScheduleDetailEntity() })
                    .andThen(Single.defer { cache.getScheduleDetail(scheduleIdx) })
            }
            .map { it.toDataEntity() }
    }

    override fun insertSchedule(scheduleData: ScheduleData): Completable {
        return cache.insertScheduleDetail(scheduleData.toScheduleDetailEntity())
    }

    override fun updateSchedule(scheduleData: ScheduleData): Completable {
        return cache.updateSchedule(
            scheduleData.toDbEntity(),
            scheduleData.partList.map { it.toDbEntity(scheduleData.idx) },
            scheduleData.relatedVideoList.map { it.toDbEntity(scheduleData.idx) }
        )
    }
}