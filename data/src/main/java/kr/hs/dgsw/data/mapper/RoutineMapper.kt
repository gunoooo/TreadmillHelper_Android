package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.domain.entity.workout.Routine

fun Routine.toDataEntity(): RoutineData {
    return RoutineData(
        title = title,
        routineType = routineType,
        partList = partList.map { it.toDataEntity() },
        relatedVideoList = relatedVideoList.map { it.toDataEntity() }
    )
}

fun Routine.toDbEntity(): RoutineEntity {
    return RoutineEntity(
        title = title,
        routineType = routineType
    )
}

fun Routine.toRoutineDetailEntity(): RoutineDetailEntity {
    return RoutineDetailEntity(
        routine = toDbEntity(),
        partList = partList.map { it.toDbEntity(idx) },
        relatedVideoList = relatedVideoList.map { it.toDbEntity(idx) }
    )
}

fun RoutineData.toEntity(): Routine {
    return Routine(
        idx = idx,
        title = title,
        routineType = routineType,
        partList = partList.map { it.toEntity() },
        relatedVideoList = relatedVideoList.map { it.toEntity() }
    )
}

fun RoutineData.toDbEntity(): RoutineEntity {
    return RoutineEntity(
        title = title,
        routineType = routineType
    )
}

fun RoutineData.toRoutineDetailEntity(): RoutineDetailEntity {
    return RoutineDetailEntity(
        routine = toDbEntity(),
        partList = partList.map { it.toDbEntity(idx) },
        relatedVideoList = relatedVideoList.map { it.toDbEntity(idx) }
    )
}

fun RoutineEntity.toEntity(): Routine {
    return Routine(
        idx = idx,
        title = title,
        routineType = routineType,
        partList = emptyList(),
        relatedVideoList = emptyList()
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

fun RoutineEntity.toRoutineDetailEntity(): RoutineDetailEntity {
    return RoutineDetailEntity(
        routine = this,
        partList = emptyList(),
        relatedVideoList = emptyList()
    )
}

fun RoutineDetailEntity.toEntity(): Routine {
    return Routine(
        idx = routine.idx,
        title = routine.title,
        routineType = routine.routineType,
        partList = partList.map { it.toEntity() },
        relatedVideoList = relatedVideoList.map { it.toEntity() }
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

fun RoutineDetailEntity.toDbEntity(): RoutineEntity {
    return RoutineEntity(
        idx = routine.idx,
        title = routine.title,
        routineType = routine.routineType
    )
}