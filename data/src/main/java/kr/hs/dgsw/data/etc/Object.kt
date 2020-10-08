package kr.hs.dgsw.data.etc

import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.ScheduleType
import kr.hs.dgsw.domain.entity.VideoCategory

object Object {
    val presetScheduleList = listOf(
        ScheduleData(
            title = "Beginner",
            scheduleType = ScheduleType.PRESET,
            partList = listOf(
                PartData(
                    title = "Warm up",
                    time = 10,
                    color = Color.YELLOW,
                    speed = 5.5,
                    incline = 0
                ),
                PartData(
                    title = "Walking",
                    time = 300,
                    color = Color.GREEN,
                    speed = 7.5,
                    incline = 0
                ),
                PartData(
                    title = "Running",
                    time = 120,
                    color = Color.RED,
                    speed = 10.5,
                    incline = 0
                ),
                PartData(
                    title = "Hiking",
                    time = 180,
                    color = Color.ORANGE,
                    speed = 5.0,
                    incline = 5
                ),
                PartData(
                    title = "Cool down",
                    time = 300,
                    color = Color.BLUE,
                    speed = 4.5,
                    incline = 0
                )
            ),
            relatedVideoList = listOf(
                VideoData(
                    title = "Virtual Run | Amazing Norwegian Nature Scenery for your Virtual Treadmill Workout",
                    thumbnail = "https://i.ytimg.com/vi/VsmsGtiavUQ/original.jpg",
                    source = "VsmsGtiavUQ",
                    duration = 2460,
                    category = VideoCategory.VIRTUAL
                )
            )
        )
    )
}