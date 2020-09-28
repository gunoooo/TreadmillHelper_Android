package kr.hs.dgsw.data.datasource

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toScheduleDetailEntity
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.ScheduleType
import kr.hs.dgsw.domain.entity.VideoCategory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ScheduleDataSourceTest {
    private lateinit var scheduleDataSource: ScheduleDataSourceImpl

    private lateinit var scheduleCache: ScheduleCache

    @Before
    fun setUp() {
        scheduleCache = mock()
        scheduleDataSource = ScheduleDataSourceImpl(
            scheduleCache
        )
    }

    @Test
    fun getScheduleDetailList_cache_success() {
        val cacheReturnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 1,
                    title = "My Custom Schedule 1",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
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
                        scheduleIdx = 1,
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
            ScheduleData(
                idx = 1,
                title = "My Custom Schedule 1",
                scheduleType = ScheduleType.CUSTOM,
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
        `when`(scheduleCache.getScheduleDetailList())
            .thenReturn(Single.just(cacheReturnValue))
        scheduleDataSource.getScheduleDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleDetailList_cache_fail() {
        val exception = Exception()
        val cacheReturnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 1,
                    title = "Beginner",
                    scheduleType = ScheduleType.PRESET
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warm up",
                        time = 600,
                        color = Color.YELLOW,
                        speed = 5.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        scheduleIdx = 1,
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
                        scheduleIdx = 1,
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
            Object.presetScheduleList.map { it.toScheduleDetailEntity() }
        val returnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "Beginner",
                scheduleType = ScheduleType.PRESET,
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
        `when`(scheduleCache.getScheduleDetailList())
            .thenReturn(Single.error(exception), Single.just(cacheReturnValue))
        `when`(scheduleCache.insertScheduleDetailList(cacheParamValue))
            .thenReturn(Completable.complete())
        scheduleDataSource.getScheduleDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleDetailList_fail() {
        val exception = Exception()
        `when`(scheduleCache.getScheduleDetailList())
            .thenReturn(Single.error(exception))
        scheduleDataSource.getScheduleDetailList().test()
            .assertError(Exception::class.java)
    }
}