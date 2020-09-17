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
            entity.partList.map { PartMapper.mapToModel(it) }
        )
    }

    override fun mapToModel(entity: ScheduleEntity): Schedule {
        return Schedule(
            entity.idx,
            entity.title,
            ArrayList()
        )
    }

    override fun mapToEntity(model: Schedule): ScheduleEntity {
        return ScheduleEntity(
            model.title
        )
    }
}