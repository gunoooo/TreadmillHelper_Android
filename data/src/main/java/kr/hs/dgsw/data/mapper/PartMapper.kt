package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.domain.entity.routine.Part

fun Part.toDataEntity(): PartData {
    return PartData(
        idx = idx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartData.toEntity(): Part {
    return Part(
        idx = idx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartData.toDbEntity(idx: Int = 0, routineIdx: Int = 0): PartEntity {
    return PartEntity(
        idx = idx,
        routineIdx = routineIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartEntity.toDataEntity(): PartData {
    return PartData(
        idx = idx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}