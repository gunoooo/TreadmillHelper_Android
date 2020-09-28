package kr.hs.dgsw.data.cache

import kr.hs.dgsw.data.database.cache.ScheduleCacheImpl
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import kr.hs.dgsw.domain.entity.Color
import kr.hs.dgsw.domain.entity.ScheduleType
import kr.hs.dgsw.domain.entity.VideoCategory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ScheduleCacheTest {
    private val scheduleCache = ScheduleCacheImpl(RuntimeEnvironment.application)

    @Test
    fun insertScheduleDetail_getScheduleDetailList_success() {
        insertScheduleDetail_success()
        val returnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 1,
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
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
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        scheduleCache.getScheduleDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertScheduleDetailList_getScheduleDetailList_success() {
        insertScheduleDetailList_success()
        val returnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 1,
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        scheduleIdx = 1,
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
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 2,
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            ),
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 2,
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        scheduleIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 4,
                        scheduleIdx = 2,
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
                        scheduleIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 4,
                        scheduleIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        scheduleCache.getScheduleDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun insertScheduleDetail_success() {
        val scheduleDetailEntity = ScheduleDetailEntity(
            schedule = ScheduleEntity(
                title = "My Custom Schedule",
                scheduleType = ScheduleType.CUSTOM
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
        scheduleCache.insertScheduleDetail(scheduleDetailEntity).test()
            .assertComplete()
    }

    @Test
    fun insertScheduleDetailList_success() {
        val scheduleDetailEntityList = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
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
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
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
        scheduleCache.insertScheduleDetailList(scheduleDetailEntityList).test()
            .assertComplete()
    }

    @Test
    fun getScheduleDetail_fail() {
        val scheduleIdx = 0
        scheduleCache.getScheduleDetail(scheduleIdx).test()
            .assertError(TableEmptyException::class.java)
    }

    @Test
    fun updateSchedule_success() {
        insertScheduleDetailList_success()
        val scheduleEntity = ScheduleEntity(
            idx = 2,
            title = "My Custom Schedule 2",
            scheduleType = ScheduleType.CUSTOM
        )
        val partEntityList = listOf(
            PartEntity(
                scheduleIdx = 2,
                title = "Warming up",
                time = 300,
                color = Color.BLACK,
                speed = 6.5,
                incline = 0
            )
        )
        val videoEntityList = listOf(
            VideoEntity(
                scheduleIdx = 2,
                title = "Run With Me",
                thumbnail = "",
                source = "",
                duration = 1000,
                category = VideoCategory.RUNNING
            )
        )
        val returnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 1,
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 2,
                        scheduleIdx = 1,
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
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 2,
                        scheduleIdx = 1,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            ),
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 2,
                    title = "My Custom Schedule 2",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        scheduleIdx = 2,
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
                        scheduleIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        scheduleCache.updateSchedule(scheduleEntity, partEntityList, videoEntityList).test()
            .assertComplete()
        scheduleCache.getScheduleDetailList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun deleteSchedule_success() {
        insertScheduleDetailList_success()
        val scheduleIdx = 1
        val returnValue = listOf(
            ScheduleDetailEntity(
                schedule = ScheduleEntity(
                    idx = 2,
                    title = "My Custom Schedule",
                    scheduleType = ScheduleType.CUSTOM
                ),
                partList = listOf(
                    PartEntity(
                        idx = 3,
                        scheduleIdx = 2,
                        title = "Warming up",
                        time = 300,
                        color = Color.BLACK,
                        speed = 6.5,
                        incline = 0
                    ),
                    PartEntity(
                        idx = 4,
                        scheduleIdx = 2,
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
                        scheduleIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    ),
                    VideoEntity(
                        idx = 4,
                        scheduleIdx = 2,
                        title = "Run With Me",
                        thumbnail = "",
                        source = "",
                        duration = 1000,
                        category = VideoCategory.RUNNING
                    )
                )
            )
        )
        scheduleCache.deleteSchedule(scheduleIdx).test()
            .assertComplete()
        scheduleCache.getScheduleDetailList().test()
            .assertValue(returnValue)
    }
}