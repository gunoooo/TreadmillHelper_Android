package kr.hs.dgsw.data.datasource

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.mapper.toScheduleWithPartEntity
import kr.hs.dgsw.data.network.remote.ScheduleRemote
import kr.hs.dgsw.domain.entity.enum.Color
import kr.hs.dgsw.domain.entity.Part
import kr.hs.dgsw.domain.entity.Schedule
import kr.hs.dgsw.domain.entity.enum.ScheduleType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ScheduleDataSourceTest {
    private lateinit var scheduleDataSource: ScheduleDataSourceImpl

    private lateinit var scheduleRemote: ScheduleRemote
    private lateinit var scheduleCache: ScheduleCache

    @Before
    fun setUp() {
        scheduleRemote = mock()
        scheduleCache = mock()
        scheduleDataSource = ScheduleDataSourceImpl(
            scheduleRemote,
            scheduleCache
        )
    }

    @Test
    fun getScheduleWithPartList_cache_success() {
        val cacheReturnValue = listOf(
            ScheduleWithPartEntity(
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
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                )
            )
        )
        `when`(scheduleCache.getScheduleWithPartList())
            .thenReturn(Single.just(cacheReturnValue))
        scheduleDataSource.getScheduleWithPartList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleWithPartList_remote_success() {
        val exception = Exception()
        val remoteReturnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "Easy",
                scheduleType = ScheduleType.PRESET,
                partList = listOf(
                    PartData(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                )
            )
        )
        val cacheParamValue = remoteReturnValue.map {
            it.toScheduleWithPartEntity()
        }
        val returnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "Easy",
                scheduleType = ScheduleType.PRESET,
                partList = listOf(
                    PartData(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                )
            )
        )
        `when`(scheduleCache.getScheduleWithPartList())
            .thenReturn(Single.error(exception))
        `when`(scheduleRemote.getPresetScheduleList())
            .thenReturn(Single.just(remoteReturnValue))
        `when`(scheduleCache.insertScheduleWithPartList(cacheParamValue))
            .thenReturn(Completable.complete())
        scheduleDataSource.getScheduleWithPartList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleWithPartList_fail() {
        val exception = Exception()
        `when`(scheduleCache.getScheduleWithPartList())
            .thenReturn(Single.error(exception))
        `when`(scheduleRemote.getPresetScheduleList())
            .thenReturn(Single.error(exception))
        scheduleDataSource.getScheduleWithPartList().test()
            .assertError(exception)
    }

    @Test
    fun getScheduleList_cache_success() {
        val cacheReturnValue = listOf(
            ScheduleEntity(
                idx = 1,
                title = "My Custom Schedule 1",
                scheduleType = ScheduleType.CUSTOM
            )
        )
        val returnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "My Custom Schedule 1",
                scheduleType = ScheduleType.CUSTOM
            )
        )
        `when`(scheduleCache.getScheduleList())
            .thenReturn(Single.just(cacheReturnValue))
        scheduleDataSource.getScheduleList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleList_remote_success() {
        val exception = Exception()
        val remoteReturnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "Easy",
                scheduleType = ScheduleType.PRESET,
                partList = listOf(
                    PartData(
                        idx = 1,
                        scheduleIdx = 1,
                        title = "Warming up",
                        time = 300,
                        color = Color.RED,
                        speed = 6.5,
                        incline = 0
                    )
                )
            )
        )
        val cacheParamValue = remoteReturnValue.map {
            it.toScheduleWithPartEntity()
        }
        val returnValue = listOf(
            ScheduleData(
                idx = 1,
                title = "Easy",
                scheduleType = ScheduleType.PRESET
            )
        )
        `when`(scheduleCache.getScheduleList())
            .thenReturn(Single.error(exception))
        `when`(scheduleRemote.getPresetScheduleList())
            .thenReturn(Single.just(remoteReturnValue))
        `when`(scheduleCache.insertScheduleWithPartList(cacheParamValue))
            .thenReturn(Completable.complete())
        scheduleDataSource.getScheduleList().test()
            .assertValue(returnValue)
            .assertComplete()
    }

    @Test
    fun getScheduleList_fail() {
        val exception = Exception()
        `when`(scheduleCache.getScheduleList())
            .thenReturn(Single.error(exception))
        `when`(scheduleRemote.getPresetScheduleList())
            .thenReturn(Single.error(exception))
        scheduleDataSource.getScheduleList().test()
            .assertError(exception)
    }

    @Test
    fun getPartList_cache_success() {
        val scheduleIdx = 0
        val cacheReturnValue = listOf(
            PartEntity(
                idx = 1,
                scheduleIdx = 1,
                title = "Run",
                time = 300,
                color = Color.RED,
                speed = 10.5,
                incline = 0
            ),
            PartEntity(
                idx = 2,
                scheduleIdx = 1,
                title = "Run",
                time = 300,
                color = Color.RED,
                speed = 10.5,
                incline = 0
            )
        )
        val returnValue = listOf(
            PartData(
                idx = 1,
                scheduleIdx = 1,
                title = "Run",
                time = 300,
                color = Color.RED,
                speed = 10.5,
                incline = 0
            ),
            PartData(
                idx = 2,
                scheduleIdx = 1,
                title = "Run",
                time = 300,
                color = Color.RED,
                speed = 10.5,
                incline = 0
            )
        )
        `when`(scheduleCache.getPartList(scheduleIdx))
            .thenReturn(Single.just(cacheReturnValue))
        scheduleDataSource.getPartList(scheduleIdx).test()
            .assertValue(returnValue)
            .assertComplete()
    }
}