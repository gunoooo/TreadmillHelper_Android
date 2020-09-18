package kr.hs.dgsw.data.datasource

import com.nhaarman.mockito_kotlin.mock
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.network.remote.ScheduleRemote
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ScheduleDataSourceTest {
    private lateinit var scheduleDataSource: ScheduleDataSource

    private lateinit var scheduleRemote: ScheduleRemote
    private lateinit var scheduleCache: ScheduleCache

    @Before
    fun setUp() {
        scheduleRemote = mock()
        scheduleCache = mock()
        scheduleDataSource = ScheduleDataSource(
            scheduleRemote,
            scheduleCache
        )
    }

    @Test
    fun getScheduleList() {
        val testObserver = scheduleDataSource.getScheduleList().test()
        testObserver
            .assertComplete()
    }
}