package kr.hs.dgsw.data.network.api

import io.reactivex.Single
import kr.hs.dgsw.domain.model.schedule.Schedule
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleApi {
    @GET("schedule")
    fun getPresetScheduleList(): Single<Response<List<Schedule>>>
}