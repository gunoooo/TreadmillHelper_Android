package kr.hs.dgsw.data.etc

import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.RoutineType
import kr.hs.dgsw.domain.entity.VideoCategory

object Object {
    val presetRoutineList = listOf(
        RoutineData(
            title = "BEGINNER",
            routineType = RoutineType.PRESET,
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
                ),
                VideoData(
                    title = "Virtual Run | The Bridge | Sun Fun Run! | Treadmill Workout",
                    thumbnail = "https://i.ytimg.com/vi/shzvF4cVCv0/original.jpg",
                    source = "shzvF4cVCv0",
                    duration = 3496,
                    category = VideoCategory.VIRTUAL
                ),
                VideoData(
                    title = "Virtual Run | Relaxing Nature Scenery | Treadmill Workout",
                    thumbnail = "https://i.ytimg.com/vi/kav_pl6S9AQ/original.jpg",
                    source = "kav_pl6S9AQ",
                    duration = 1289,
                    category = VideoCategory.VIRTUAL
                ),
                VideoData(
                    title = "Virtual Run | Stunning Trails in Beautiful Nature Scenery | Treadmill Running, Walk, Workout",
                    thumbnail = "https://i.ytimg.com/vi/rlG10erTysE/original.jpg",
                    source = "rlG10erTysE",
                    duration = 2650,
                    category = VideoCategory.VIRTUAL
                ),
                VideoData(
                    title = "Virtual Run | Zen Nature Scenery for your Virtual Treadmill Workout",
                    thumbnail = "https://i.ytimg.com/vi/qZou0yB1RFk/original.jpg",
                    source = "qZou0yB1RFk",
                    duration = 2850,
                    category = VideoCategory.VIRTUAL
                )
            )
        )
    )
}