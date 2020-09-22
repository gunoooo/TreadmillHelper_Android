package kr.hs.dgsw.data.remote

import kr.hs.dgsw.data.base.BaseRemoteTest
import kr.hs.dgsw.data.network.remote.ScheduleRemoteImpl
import kr.hs.dgsw.data.network.api.ScheduleApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ScheduleRemoteTest : BaseRemoteTest() {
    private lateinit var scheduleRemote: ScheduleRemoteImpl

    @Before
    fun setUp() {
        scheduleRemote = ScheduleRemoteImpl(
            retrofit.create(ScheduleApi::class.java)
        )
    }

    @Test
    fun getPresetScheduleList_success() {
        scheduleRemote.getPresetScheduleList().test()
            .assertComplete()
    }
}