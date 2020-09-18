package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseRemote
import kr.hs.dgsw.data.network.service.ScheduleService
import kr.hs.dgsw.domain.model.schedule.Schedule

open class ScheduleRemote(
    override val service: ScheduleService
) : BaseRemote<ScheduleService>() {
    fun getPresetScheduleList(): Single<List<Schedule>> =
        service.getPresetScheduleList().map(getResponse())
}