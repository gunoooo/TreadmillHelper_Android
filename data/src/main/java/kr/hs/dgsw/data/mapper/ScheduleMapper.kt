package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.domain.model.schedule.Schedule

fun Schedule.toEntity(): ScheduleEntity {
    return ScheduleEntity(
        this.idx,
        this.title,
        this.scheduleType
    )
}

fun Schedule.toScheduleWithPartEntity(): ScheduleWithPartEntity {
    return ScheduleWithPartEntity(
        this.toEntity(),
        this.partList.map { it.toEntity() }
    )
}

fun ScheduleEntity.toModel(): Schedule {
    return Schedule(
        this.idx,
        this.title,
        this.scheduleType,
        emptyList()
    )
}

fun ScheduleWithPartEntity.toModel(): Schedule {
    return Schedule(
        this.schedule.idx,
        this.schedule.title,
        this.schedule.scheduleType,
        this.partList.map { it.toModel() }
    )
}

fun ScheduleWithPartEntity.toEntity(): ScheduleEntity {
    return ScheduleEntity(
        this.schedule.idx,
        this.schedule.title,
        this.schedule.scheduleType
    )
}