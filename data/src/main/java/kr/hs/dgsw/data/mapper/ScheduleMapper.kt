package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.base.BaseEntityMapper
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.domain.model.schedule.Schedule

object ScheduleMapper : BaseEntityMapper<Schedule, ScheduleEntity> {
    fun mapToModel(entity: ScheduleWithPartEntity): Schedule {
        return Schedule(
            entity.schedule.idx,
            entity.schedule.title,
            entity.schedule.scheduleType,
            entity.partList.map { PartMapper.mapToModel(it) }
        )
    }

    override fun mapToModel(entity: ScheduleEntity): Schedule {
        return Schedule(
            entity.idx,
            entity.title,
            entity.scheduleType,
            ArrayList()
        )
    }

    fun mapToWithPartEntity(model: Schedule): ScheduleWithPartEntity {
        return ScheduleWithPartEntity(
            mapToEntity(model),
            model.partList.map { PartMapper.mapToEntity(it) }
        )
    }

    override fun mapToEntity(model: Schedule): ScheduleEntity {
        return ScheduleEntity(
            model.title,
            model.scheduleType
        )
    }
}