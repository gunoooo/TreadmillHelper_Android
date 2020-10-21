package kr.hs.dgsw.data.remote

import kr.hs.dgsw.data.base.BaseRemoteTest
import kr.hs.dgsw.data.network.remote.RoutineRemoteImpl
import kr.hs.dgsw.data.network.api.RoutineApi
import kr.hs.dgsw.data.network.api.VideoApi
import kr.hs.dgsw.data.network.remote.VideoRemoteImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VideoRemoteTest : BaseRemoteTest() {
    private lateinit var videoRemote: VideoRemoteImpl

    @Before
    fun setUp() {
        videoRemote = VideoRemoteImpl(
            retrofit.create(VideoApi::class.java)
        )
    }

    @Test
    fun getPresetRoutineList_success() {
        videoRemote.getVideoList(1, 5).test()
            .assertComplete()
    }
}