package kr.hs.dgsw.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseDataSource
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.mapper.PartMapper
import kr.hs.dgsw.data.mapper.ScheduleMapper
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.model.schedule.Schedule
import javax.inject.Inject

class ScheduleDataSource @Inject constructor(
    override val remote: Any,
    override val cache: ScheduleCache
) : BaseDataSource<Any, ScheduleCache>() {
    fun getScheduleWithPartList(): Single<List<Schedule>> {
        return cache.getScheduleWithPartList().map { scheduleList ->
            scheduleList.map {
                ScheduleMapper.mapToModel(it)
            }
        }
    }

    fun getScheduleList(): Single<List<Schedule>> {
        return cache.getScheduleList().map { scheduleList ->
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
}