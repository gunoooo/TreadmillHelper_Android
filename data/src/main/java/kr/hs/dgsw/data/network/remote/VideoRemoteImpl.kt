package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseRemote
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.data.network.api.VideoApi
import javax.inject.Inject

class VideoRemoteImpl @Inject constructor(
    override val api: VideoApi
) : BaseRemote<VideoApi>(), VideoRemote {
    override fun getVideoList(
        page: Int,
        size: Int
    ): Single<List<VideoData>> {
        return api.getVideoList(page, size)
            .map(getResponse())
            .map { it.videos }
    }

    override fun getVideoDetail(url: String): Single<VideoData> {
        return api.getVideoDetail(url)
            .map(getResponse())
    }
}