package kr.hs.dgsw.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.data.entity.VideoData

interface VideoDataSource {
    fun getVideoList(): Single<List<VideoData>>
}