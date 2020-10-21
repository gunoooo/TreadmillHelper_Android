package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.domain.entity.routine.Routine

fun Routine.toDataEntity(): RoutineData {
    return RoutineData(
        title = title,
        routineType = routineType.name,
        partList = partList.map { it.toDataEntity() },
        relatedVideoList = relatedVideoList.map { it.toDataEntity() }
    )
}

fun RoutineData.toEntity(): Routine {
    return Routine(
        idx = idx,
        title = title,
        routineType = enumValueOf(routineType),
        partList = partList.map { it.toEntity() },
        relatedVideoList = relatedVideoList.map { it.toEntity() }
    )
}

fun RoutineData.toDbEntity(idx: Int = 0): RoutineEntity {
    return RoutineEntity(
        idx = idx,
        title = title,
        routineType = routineType
    )
}

fun RoutineData.toRoutineDetailEntity(idx: Int = 0): RoutineDetailEntity {
    return RoutineDetailEntity(
        routine = toDbEntity(idx),
        partList = partList.map { it.toDbEntity(idx) },
        relatedVideoList = relatedVideoList.map { it.toDbEntity(idx) }
    )
}

fun RoutineEntity.toDataEntity(): RoutineData {
    return RoutineData(
        idx = idx,
        title = title,
        routineType = routineType,
        partList = emptyList(),
        relatedVideoList = emptyList()
    )
}

fun RoutineDetailEntity.toDataEntity(): RoutineData {
    return RoutineData(
        idx = routine.idx,
        title = routine.title,
        routineType = routine.routineType,
        partList = partList.map { it.toDataEntity() },
        relatedVideoList = relatedVideoList.map { it.toDataEntity() }
    )
}