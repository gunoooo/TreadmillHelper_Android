package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.domain.model.schedule.Part

fun Part.toEntity(): PartEntity {
    return PartEntity(
        this.idx,
        this.scheduleIdx,
        this.title,
        this.time,
        this.color,
        this.speed,
        this.incline
    )
}

fun PartEntity.toModel(): Part {
    return Part(
        this.idx,
        this.scheduleIdx,
        this.title,
        this.time,
        this.color,
        this.speed,
        this.incline
    )
}