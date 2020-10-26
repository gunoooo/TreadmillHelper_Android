package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.entity.VideoData

interface VideoRemote {
    fun getVideoList(
        page: Int,
        size: Int
    ): Single<List<VideoData>>

    fun getVideoDetail(
        url: String
    ): Single<VideoData>
}