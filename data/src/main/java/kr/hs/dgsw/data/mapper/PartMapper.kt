package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.domain.entity.Part

fun Part.toDataEntity(): PartData {
    return PartData(
        idx = idx,
        scheduleIdx = scheduleIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun Part.toDbEntity(): PartEntity {
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

fun PartData.toEntity(): Part {
    return Part(
        idx = idx,
        scheduleIdx = scheduleIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartData.toDbEntity(): PartEntity {
    return PartEntity(
        idx = idx,
        scheduleIdx = scheduleIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartEntity.toEntity(): Part {
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

fun PartEntity.toDataEntity(): PartData {
    return PartData(
        idx = idx,
        scheduleIdx = scheduleIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}