package kr.hs.dgsw.data.mapper

import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.entity.RelatedVideoData
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.video.Video

fun RelatedVideo.toVideo(): Video {
    return Video(
        source = source,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        category = category
    )
}

fun RelatedVideo.toDataEntity(): RelatedVideoData {
    return RelatedVideoData(
        idx = idx,
        source = source,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        category = category
    )
}

fun RelatedVideoData.toEntity(): RelatedVideo {
    return RelatedVideo(
        idx = idx,
        source = source,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        category = category
    )
}

fun RelatedVideoData.toDbEntity(idx: Int = 0, routineIdx: Int = 0): RelatedVideoEntity {
    return RelatedVideoEntity(
        idx = idx,
        routineIdx = routineIdx,
        source = source,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        category = category
    )
}

fun RelatedVideoEntity.toDataEntity(): RelatedVideoData {
    return RelatedVideoData(
        idx = idx,
        source = source,
        title = title,
        thumbnail = thumbnail,
        duration = duration,
        category = category
    )
}