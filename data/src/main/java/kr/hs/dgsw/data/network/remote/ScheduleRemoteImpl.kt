package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseRemote
import kr.hs.dgsw.data.network.api.ScheduleApi
import kr.hs.dgsw.domain.model.schedule.Schedule

class ScheduleRemoteImpl(
    override val api: ScheduleApi
) : BaseRemote<ScheduleApi>(), ScheduleRemote {
    override fun getPresetScheduleList(): Single<List<Schedule>> =
        api.getPresetScheduleList().map(getResponse())
}