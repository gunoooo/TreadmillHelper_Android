package kr.hs.dgsw.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.domain.entity.video.Video

interface VideoRepository {
    fun getVideoList(): Single<List<Video>>
}