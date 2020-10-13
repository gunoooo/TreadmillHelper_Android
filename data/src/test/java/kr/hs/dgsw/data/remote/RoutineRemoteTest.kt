package kr.hs.dgsw.data.remote

import kr.hs.dgsw.data.base.BaseRemoteTest
import kr.hs.dgsw.data.network.remote.RoutineRemoteImpl
import kr.hs.dgsw.data.network.api.RoutineApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RoutineRemoteTest : BaseRemoteTest() {
    private lateinit var routineRemote: RoutineRemoteImpl

    @Before
    fun setUp() {
        routineRemote = RoutineRemoteImpl(
            retrofit.create(RoutineApi::class.java)
        )
    }

    @Test
    fun getPresetRoutineList_success() {
        routineRemote.getPresetRoutineList().test()
            .assertComplete()
    }
}