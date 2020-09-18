package kr.hs.dgsw.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.domain.model.schedule.Schedule
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleService {
    @GET("")
    fun getPresetScheduleList(): Single<Response<List<Schedule>>>
}