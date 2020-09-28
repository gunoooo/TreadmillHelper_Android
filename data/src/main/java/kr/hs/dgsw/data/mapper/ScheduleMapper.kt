package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.domain.entity.schedule.Schedule

fun Schedule.toDataEntity(): ScheduleData {
    return ScheduleData(
        title = title,
        scheduleType = scheduleType,
        partList = partList.map { it.toDataEntity() },
        relatedVideoList = relatedVideoList.map { it.toDataEntity() }
    )
}

fun Schedule.toDbEntity(): ScheduleEntity {
    return ScheduleEntity(
        title = title,
        scheduleType = scheduleType
    )
}

fun Schedule.toScheduleDetailEntity(): ScheduleDetailEntity {
    return ScheduleDetailEntity(
        schedule = toDbEntity(),
        partList = partList.map { it.toDbEntity(idx) },
        relatedVideoList = relatedVideoList.map { it.toDbEntity(idx) }
    )
}

fun ScheduleData.toEntity(): Schedule {
    return Schedule(
        idx = idx,
        title = title,
        scheduleType = scheduleType,
        partList = partList.map { it.toEntity() }
    )
}

fun ScheduleData.toDbEntity(): ScheduleEntity {
    return ScheduleEntity(
        title = title,
        scheduleType = scheduleType
    )
}

fun ScheduleData.toScheduleDetailEntity(): ScheduleDetailEntity {
    return ScheduleDetailEntity(
        schedule = toDbEntity(),
        partList = partList.map { it.toDbEntity(idx) },
        relatedVideoList = relatedVideoList.map { it.toDbEntity(idx) }
    )
}

fun ScheduleEntity.toEntity(): Schedule {
    return Schedule(
        idx = idx,
        title = title,
        scheduleType = scheduleType,
        partList = emptyList()
    )
}

fun ScheduleEntity.toDataEntity(): ScheduleData {
    return ScheduleData(
        idx = idx,
        title = title,
        scheduleType = scheduleType,
        partList = emptyList(),
        relatedVideoList = emptyList()
    )
}

fun ScheduleEntity.toScheduleDetailEntity(): ScheduleDetailEntity {
    return ScheduleDetailEntity(
        schedule = this,
        partList = emptyList(),
        relatedVideoList = emptyList()
    )
}

fun ScheduleDetailEntity.toEntity(): Schedule {
    return Schedule(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType,
        partList = partList.map { it.toEntity() }
    )
}

fun ScheduleDetailEntity.toDataEntity(): ScheduleData {
    return ScheduleData(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType,
        partList = partList.map { it.toDataEntity() },
        relatedVideoList = relatedVideoList.map { it.toDataEntity() }
    )
}

fun ScheduleDetailEntity.toDbEntity(): ScheduleEntity {
    return ScheduleEntity(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType
    )
}