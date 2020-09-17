package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.base.BaseEntityMapper
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.domain.model.schedule.Part

object PartMapper : BaseEntityMapper<Part, PartEntity> {
    override fun mapToModel(entity: PartEntity): Part {
        return Part(
            entity.idx,
            entity.scheduleIdx,
            entity.title,
            entity.time,
            entity.color,
            entity.speed,
            entity.incline
        )
    }

    override fun mapToEntity(model: Part): PartEntity {
        return PartEntity(
            model.scheduleIdx,
            model.title,
            model.time,
            model.color,
            model.speed,
            model.incline
        )
    }
}