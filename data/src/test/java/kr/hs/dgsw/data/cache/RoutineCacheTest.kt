package kr.hs.dgsw.data.cache

import kr.hs.dgsw.data.database.cache.RoutineCacheImpl
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.etc.Color
import kr.hs.dgsw.data.exception.TableEmptyException
import kr.hs.dgsw.domain.entity.e.RoutineTypeEnum
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
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
                    routineType = RoutineTypeEnum.CUSTOM.name
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
                    RelatedVideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            )
        )
        routineCache.getRoutineList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertRoutineDetailList_getRoutineDetailList_success() {
        insertRoutineDetailList_success()
        val returnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 2,
                    title = "My Custom Routine",
                    routineType = RoutineTypeEnum.CUSTOM.name
                ),
                partList = listOf(
                    PartEntity(
                        idx = 2,
                        routineIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
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
                    RelatedVideoEntity(
                        idx = 2,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            ),
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 3,
                    title = "My Custom Routine",
                    routineType = RoutineTypeEnum.CUSTOM.name
                ),
                partList = listOf(
                    PartEntity(
                        idx = 4,
                        routineIdx = 3,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 5,
                        routineIdx = 3,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    RelatedVideoEntity(
                        idx = 4,
                        routineIdx = 3,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        idx = 5,
                        routineIdx = 3,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            )
        )
        routineCache.getRoutineList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertRoutineDetail_success() {
        val routineDetailEntity = RoutineDetailEntity(
            routine = RoutineEntity(
                title = "My Custom Routine",
                routineType = RoutineTypeEnum.CUSTOM.name
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
                RelatedVideoEntity(
                    idx = 1,
                    routineIdx = 1,
                    title = "Run With Me",
                    thumbnail = "",
                    source = "",
                    duration = 1000,
                    category = VideoCategoryEnum.RUNNING.name
                ),
                RelatedVideoEntity(
                    idx = 2,
                    routineIdx = 1,
                    title = "Run With Me",
                    thumbnail = "",
                    source = "",
                    duration = 1000,
                    category = VideoCategoryEnum.RUNNING.name
                )
            )
        )
        routineCache.insertRoutine(routineDetailEntity).test()
            .assertComplete()
    }

    @Test
    fun insertRoutineDetailList_success() {
        val routineDetailEntityList = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    title = "My Custom Routine",
                    routineType = RoutineTypeEnum.CUSTOM.name
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
                    RelatedVideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            ),
            RoutineDetailEntity(
                routine = RoutineEntity(
                    title = "My Custom Routine",
                    routineType = RoutineTypeEnum.CUSTOM.name
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
                    RelatedVideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            )
        )
        routineCache.insertRoutineList(routineDetailEntityList).test()
            .assertComplete()
    }

    @Test
    fun getRoutineDetail_fail() {
        val routineIdx = 0
        routineCache.getRoutine(routineIdx).test()
            .assertError(TableEmptyException::class.java)
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
                    routineType = RoutineTypeEnum.CUSTOM.name
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
                    RelatedVideoEntity(
                        idx = 3,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    ),
                    RelatedVideoEntity(
                        idx = 4,
                        routineIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategoryEnum.RUNNING.name
                    )
                )
            )
        )
        routineCache.deleteRoutine(routineIdx).test()
            .assertComplete()
        routineCache.getRoutineList().test()
            .assertValue(returnValue)
    }
}