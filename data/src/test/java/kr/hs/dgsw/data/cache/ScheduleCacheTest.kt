package kr.hs.dgsw.data.cache

import kr.hs.dgsw.data.database.cache.ScheduleCacheImpl
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import kr.hs.dgsw.domain.model.Color
import kr.hs.dgsw.domain.model.schedule.ScheduleType
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
    fun insertScheduleList_getScheduleList_success() {
        insertScheduleList_success()
        val returnScheduleEntityList = listOf(
            ScheduleEntity(
                1,
                "My Custom Schedule",
                ScheduleType.CUSTOM
            ),
            ScheduleEntity(
                2,
                "My Custom Schedule 2",
                ScheduleType.CUSTOM
            )
        )
        scheduleCache.getScheduleList().test()
            .assertValue(returnScheduleEntityList)
            .assertComplete()
    }

    @Test
    fun getScheduleList_empty() {
        scheduleCache.getScheduleList().test()
            .assertError(TableEmptyException::class.java)
    }

    @Test
    fun insertScheduleWithPartList_getScheduleWithPartList_success() {
        insertScheduleWithPartList_success()
        val returnScheduleWithPartEntityList = listOf(
            ScheduleWithPartEntity(
                ScheduleEntity(
                    1,
                    "My Custom Schedule",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    ),
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            ),
            ScheduleWithPartEntity(
                ScheduleEntity(
                    2,
                    "My Custom Schedule 2",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        2,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            )
        )
        scheduleCache.getScheduleWithPartList().test()
            .assertValue(returnScheduleWithPartEntityList)
            .assertComplete()
    }

    @Test
    fun getScheduleWithPartList_empty() {
        scheduleCache.getScheduleWithPartList().test()
            .assertError(TableEmptyException::class.java)
    }

    @Test
    fun insertScheduleWithPartList_getPartList_success() {
        insertScheduleWithPartList_success()
        val scheduleIdx = 1
        val returnPartEntityList = listOf(
            PartEntity(
                scheduleIdx,
                "Warming up",
                300,
                Color.BLACK,
                6.5,
                0
            ),
            PartEntity(
                scheduleIdx,
                "Warming up",
                300,
                Color.BLACK,
                6.5,
                0
            )
        )
        scheduleCache.getPartList(scheduleIdx).test()
            .assertValue(returnPartEntityList)
            .assertComplete()
    }

    @Test
    fun getPartList_empty() {
        val scheduleIdx = 1
        scheduleCache.getPartList(scheduleIdx).test()
            .assertError(TableEmptyException::class.java)
    }

    @Test
    fun insertScheduleList_success() {
        val scheduleEntityList = listOf(
            ScheduleEntity(
                "My Custom Schedule",
                ScheduleType.CUSTOM
            ),
            ScheduleEntity(
                "My Custom Schedule 2",
                ScheduleType.CUSTOM
            )
        )
        scheduleCache.insertScheduleList(scheduleEntityList).test()
            .assertComplete()
    }

    @Test
    fun insertScheduleWithPartList_success() {
        val scheduleWithPartEntityList = listOf(
            ScheduleWithPartEntity(
                ScheduleEntity(
                    "My Custom Schedule",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    ),
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            ),
            ScheduleWithPartEntity(
                ScheduleEntity(
                    "My Custom Schedule 2",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        2,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            )
        )
        scheduleCache.insertScheduleWithPartList(scheduleWithPartEntityList).test()
            .assertComplete()
    }

    @Test
    fun insertSchedule_success() {
        val scheduleEntity = ScheduleEntity(
            "My Custom Schedule",
            ScheduleType.CUSTOM
        )
        scheduleCache.insertSchedule(scheduleEntity).test()
            .assertComplete()
    }

    @Test
    fun updateSchedule_success() {
        insertScheduleWithPartList_success()
        val scheduleEntity = ScheduleEntity(
            2,
            "Special Schedule",
            ScheduleType.CUSTOM
        )
        val partEntityList = listOf(
            PartEntity(
                2,
                "Run",
                300,
                Color.RED,
                10.5,
                0
            )
        )
        val returnScheduleWithPartEntityList = listOf(
            ScheduleWithPartEntity(
                ScheduleEntity(
                    1,
                    "My Custom Schedule",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    ),
                    PartEntity(
                        1,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            ),
            ScheduleWithPartEntity(
                ScheduleEntity(
                    2,
                    "Special Schedule",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        2,
                        "Run",
                        300,
                        Color.RED,
                        10.5,
                        0
                    )
                )
            )
        )
        scheduleCache.updateSchedule(scheduleEntity, partEntityList).test()
            .assertComplete()
        scheduleCache.getScheduleWithPartList().test()
            .assertValue(returnScheduleWithPartEntityList)
            .assertComplete()
    }

    @Test
    fun deleteSchedule_success() {
        insertScheduleWithPartList_success()
        val scheduleIdx = 1
        val returnScheduleWithPartEntityList = listOf(
            ScheduleWithPartEntity(
                ScheduleEntity(
                    2,
                    "My Custom Schedule 2",
                    ScheduleType.CUSTOM
                ),
                listOf(
                    PartEntity(
                        2,
                        "Warming up",
                        300,
                        Color.BLACK,
                        6.5,
                        0
                    )
                )
            )
        )
        scheduleCache.deleteSchedule(scheduleIdx).test()
            .assertComplete()
        scheduleCache.getScheduleWithPartList().test()
            .assertValue(returnScheduleWithPartEntityList)
    }

    @Test
    fun deletePart_success() {
        insertScheduleWithPartList_success()
        val scheduleIdx = 1
        scheduleCache.deletePart(scheduleIdx).test()
            .assertComplete()
        scheduleCache.getPartList(scheduleIdx).test()
            .assertError(TableEmptyException::class.java)
    }
}