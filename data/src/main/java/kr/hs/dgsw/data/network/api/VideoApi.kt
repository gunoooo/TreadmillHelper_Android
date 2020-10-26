package kr.hs.dgsw.data.network.api

import io.reactivex.Single
import kr.hs.dgsw.data.dto.response.VideoResponse
import kr.hs.dgsw.data.entity.VideoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {
    @GET("video")
    fun getVideoList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<VideoResponse>>

    @GET("video/detail")
    fun getVideoDetail(
        @Query("url") url: String
    ): Single<Response<VideoData>>
}