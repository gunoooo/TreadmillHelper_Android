package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.domain.entity.routine.Part

fun Part.toDataEntity(routineIdx: Int): PartData {
    return PartData(
        idx = idx,
        routineIdx = routineIdx,
        title = title,
        time = time,
        color = color.name,
        speed = speed,
        incline = incline
    )
}

fun PartData.toEntity(): Part {
    return Part(
        idx = idx,
        title = title,
        time = time,
        color = enumValueOf(color),
        speed = speed,
        incline = incline
    )
}

fun PartData.toDbEntity(): PartEntity {
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
        routineIdx = routineIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}