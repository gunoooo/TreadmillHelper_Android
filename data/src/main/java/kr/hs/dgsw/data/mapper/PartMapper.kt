package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.domain.entity.workout.Part

fun Part.toDataEntity(): PartData {
    return PartData(
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun Part.toDbEntity(routineIdx: Int): PartEntity {
    return PartEntity(
        routineIdx = routineIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartData.toEntity(): Part {
    return Part(
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartData.toDbEntity(routineIdx: Int): PartEntity {
    return PartEntity(
        routineIdx = routineIdx,
        title = title,
        time = time,
        color = color,
        speed = speed,
        incline = incline
    )
}

fun PartEntity.toEntity(): Part {
    return Part(
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