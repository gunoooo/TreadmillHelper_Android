package kr.hs.dgsw.data.network.api

import io.reactivex.Single
import kr.hs.dgsw.data.dto.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {
    @GET("video")
    fun getVideoList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<Response<VideoResponse>>
}