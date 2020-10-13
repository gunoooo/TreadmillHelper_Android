package kr.hs.dgsw.data.datasource

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.RoutineCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toRoutineDetailEntity
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.RoutineType
import kr.hs.dgsw.domain.entity.VideoCategory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class RoutineDataSourceTest {
    private lateinit var routineDataSource: RoutineDataSourceImpl

    private lateinit var routineCache: RoutineCache

    @Before
    fun setUp() {
        routineCache = mock()
        routineDataSource = RoutineDataSourceImpl(
            routineCache
        )
    }

    @Test
    fun getRoutineDetailList_cache_success() {
        val cacheReturnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 1,
                    title = "My Custom Routine 1",
                    routineType = RoutineType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Run With Me!",
                        thumbnail = "",
                        source = "",
                        duration = 160,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        val returnValue = listOf(
            RoutineData(
                idx = 1,
                title = "My Custom Routine 1",
                routineType = RoutineType.CUSTOM,
                partList = listOf(
                    PartData(
                        idx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoData(
                        idx = 1,
                        title = "Run With Me!",
                        thumbnail = "",
                        source = "",
                        duration = 160,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        `when`(routineCache.getRoutineDetailList())
            .thenReturn(Single.just(cacheReturnValue))
        routineDataSource.getRoutineDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getRoutineDetailList_cache_fail() {
        val exception = Exception()
        val cacheReturnValue = listOf(
            RoutineDetailEntity(
                routine = RoutineEntity(
                    idx = 1,
                    title = "Beginner",
                    routineType = RoutineType.PRESET
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Warm up",
                        time = 600,
                        color = Color.YELLOW,
                        speed = 5.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        routineIdx = 1,
                        title = "Walking",
                        time = 300,
                        color = Color.GREEN,
                        speed = 7.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoEntity(
                        idx = 1,
                        routineIdx = 1,
                        title = "Virtual Run | Amazing Norwegian Nature Scenery for your Virtual Treadmill Workout",
                        thumbnail = "",
                        source = "",
                        duration = 2460,
                        category = VideoCategory.VIRTUAL
                    )
                )
            )
        )
        val cacheParamValue =
            Object.presetRoutineList.map { it.toRoutineDetailEntity() }
        val returnValue = listOf(
            RoutineData(
                idx = 1,
                title = "Beginner",
                routineType = RoutineType.PRESET,
                partList = listOf(
                    PartData(
                        idx = 1,
                        title = "Warm up",
                        time = 600,
                        color = Color.YELLOW,
                        speed = 5.5,
                        incline = 0
                    ),
                    PartData(
                        idx = 2,
                        title = "Walking",
                        time = 300,
                        color = Color.GREEN,
                        speed = 7.5,
                        incline = 0
                    )
                ),
                relatedVideoList = listOf(
                    VideoData(
                        idx = 1,
                        title = "Virtual Run | Amazing Norwegian Nature Scenery for your Virtual Treadmill Workout",
                        thumbnail = "",
                        source = "",
                        duration = 2460,
                        category = VideoCategory.VIRTUAL
                    )
                )
            )
        )
        `when`(routineCache.getRoutineDetailList())
            .thenReturn(Single.error(exception), Single.just(cacheReturnValue))
        `when`(routineCache.insertRoutineDetailList(cacheParamValue))
            .thenReturn(Completable.complete())
        routineDataSource.getRoutineDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getRoutineDetailList_fail() {
        val exception = Exception()
        `when`(routineCache.getRoutineDetailList())
            .thenReturn(Single.error(exception))
        routineDataSource.getRoutineDetailList().test()
            .assertError(Exception::class.java)
    }
}