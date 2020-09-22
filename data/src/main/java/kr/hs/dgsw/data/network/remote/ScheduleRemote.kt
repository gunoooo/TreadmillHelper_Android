package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.domain.model.schedule.Schedule

interface ScheduleRemote {
    fun getPresetScheduleList(): Single<List<Schedule>>
}