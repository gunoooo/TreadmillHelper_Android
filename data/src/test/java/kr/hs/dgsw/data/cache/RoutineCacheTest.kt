package kr.hs.dgsw.data.cache

import kr.hs.dgsw.data.database.cache.RoutineCacheImpl
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.RoutineType
import kr.hs.dgsw.domain.entity.VideoCategory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class RoutineCacheTest {
    private val routineCache = RoutineCacheImpl(RuntimeEnvironment.application)

    @Test
    fun insertRoutineDetail_getRoutineDetailList_success() {
        insertRoutineDetail_success()
        val returnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 1,
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        routineCache.getRoutineDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertRoutineDetailList_getRoutineDetailList_success() {
        insertRoutineDetailList_success()
        val returnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 1,
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 2,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            ),
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 2,
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 4,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 4,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        routineCache.getRoutineDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertRoutineDetail_success() {
        val routineDetailEntity = RoutineDetailEntity(
            routine = RoutineEntity(
                title = "My Custom Routine",
                routineType = RoutineType.CUSTOM
            ),
            partList = listOf(
                PartEntity(
                    title = "Warming up",
                    time = 300,
                    color = Color.BLACK,
                    speed = 6.5,
                    incline = 0
                ),
                PartEntity(
                    title = "Warming up",
                    time = 300,
                    color = Color.BLACK,
                    speed = 6.5,
                    incline = 0
                )
            ),
            relatedVideoList = listOf(
                VideoEntity(
                    title = "Run With Me",
                    thumbnail = "",
                    source = "",
                    duration = 1000,
                    category = VideoCategory.RUNNING
                ),
                VideoEntity(
                    title = "Run With Me",
                    thumbnail = "",
                    source = "",
                    duration = 1000,
                    category = VideoCategory.RUNNING
                )
            )
        )
        routineCache.insertRoutineDetail(routineDetailEntity).test()
            .assertComplete()
    }

    @Test
    fun insertRoutineDetailList_success() {
        val routineDetailEntityList = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            ),
            RoutineDetailEntity(
                routine = RoutineEntity(
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        routineCache.insertRoutineDetailList(routineDetailEntityList).test()
            .assertComplete()
    }

    @Test
    fun getRoutineDetail_fail() {
        val routineIdx = 0
        routineCache.getRoutineDetail(routineIdx).test()
            .assertError(TableEmptyException::class.java)
    }

    @Test
    fun updateRoutine_success() {
        insertRoutineDetailList_success()
        val routineEntity = RoutineEntity(
            idx = 2,
            title = "My Custom Routine 2",
            routineType = RoutineType.CUSTOM
        )
        val partEntityList = listOf(
            PartEntity(
                routineIdx = 2,
                title = "Warming up",
                time = 300,
                color = Color.BLACK,
                speed = 6.5,
                incline = 0
            )
        )
        val videoEntityList = listOf(
            VideoEntity(
                routineIdx = 2,
                title = "Run With Me",
                thumbnail = "",
                source = "",
                duration = 1000,
                category = VideoCategory.RUNNING
            )
        )
        val returnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 1,
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 2,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            ),
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 2,
                    title = "My Custom Routine 2",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        routineCache.updateRoutine(routineEntity, partEntityList, videoEntityList).test()
            .assertComplete()
        routineCache.getRoutineDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun deleteRoutine_success() {
        insertRoutineDetailList_success()
        val routineIdx = 1
        val returnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 2,
                    title = "My Custom Routine",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 4,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 4,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        routineCache.deleteRoutine(routineIdx).test()
            .assertComplete()
        routineCache.getRoutineDetailList().test()
            .assertValue(returnValue)
    }
}