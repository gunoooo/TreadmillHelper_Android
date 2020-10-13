package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.domain.entity.video.Video

fun Video.toDataEntity(): VideoData {
    return VideoData(
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}

fun Video.toDbEntity(routineIdx: Int): VideoEntity {
    return VideoEntity(
        routineIdx = routineIdx,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}

fun VideoData.toEntity(): Video {
    return Video(
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}

fun VideoData.toDbEntity(routineIdx: Int): VideoEntity {
    return VideoEntity(
        routineIdx = routineIdx,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}

fun VideoEntity.toEntity(): Video {
    return Video(
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}

fun VideoEntity.toDataEntity(): VideoData {
    return VideoData(
        idx = idx,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        source = source,
        category = category
    )
}