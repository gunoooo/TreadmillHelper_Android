package kr.hs.dgsw.data.remote

import kr.hs.dgsw.data.base.BaseRemoteTest
import kr.hs.dgsw.data.network.api.VideoApi
import kr.hs.dgsw.data.network.remote.VideoRemoteImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.regex.Matcher
import java.util.regex.Pattern

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
    fun getVideoList_success() {
        videoRemote.getVideoList(1, 5).test()
            .assertComplete()
    }

    @Test
    fun getVideoDetail_success() {
        videoRemote.getVideoDetail("https://www.youtube.com/watch?v=OYdbBBNN9bY").test()
            .assertComplete()
    }

    @Test
    fun test() {
        val reg =
            "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)"
        val pattern: Pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher("https://www.youtub.com/watch?v=IynuN_51xiE")

        if (matcher.find())
            print(matcher.group(1))
        else
            print("nope")
    }
}