package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.domain.entity.Schedule

fun Schedule.toDataEntity(): ScheduleData {
    return ScheduleData(
        idx = idx,
        title = title,
        scheduleType = scheduleType,
        partList = partList.map { it.toDataEntity() }
    )
}

fun Schedule.toDbEntity(): ScheduleEntity {
    return ScheduleEntity(
        idx = idx,
        title = title,
        scheduleType = scheduleType
    )
}

fun Schedule.toScheduleWithPartEntity(): ScheduleWithPartEntity {
    return ScheduleWithPartEntity(
        schedule = toDbEntity(),
        partList = partList.map { it.toDbEntity() }
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
        idx = idx,
        title = title,
        scheduleType = scheduleType
    )
}

fun ScheduleData.toScheduleWithPartEntity(): ScheduleWithPartEntity {
    return ScheduleWithPartEntity(
        schedule = this.toDbEntity(),
        partList = partList.map { it.toDbEntity() }
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
        partList = emptyList()
    )
}

fun ScheduleEntity.toScheduleWithPartEntity(): ScheduleWithPartEntity {
    return ScheduleWithPartEntity(
        schedule = this,
        partList = emptyList()
    )
}

fun ScheduleWithPartEntity.toEntity(): Schedule {
    return Schedule(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType,
        partList = partList.map { it.toEntity() }
    )
}

fun ScheduleWithPartEntity.toDataEntity(): ScheduleData {
    return ScheduleData(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType,
        partList = partList.map { it.toDataEntity() }
    )
}

fun ScheduleWithPartEntity.toDbEntity(): ScheduleEntity {
    return ScheduleEntity(
        idx = schedule.idx,
        title = schedule.title,
        scheduleType = schedule.scheduleType
    )
}