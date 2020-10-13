package kr.hs.dgsw.data.network.api

import io.reactivex.Single
import kr.hs.dgsw.data.dto.response.RoutineResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoutineApi {
    @GET("schedule")
    fun getPresetRoutineList(): Single<Response<RoutineResponse>>
}