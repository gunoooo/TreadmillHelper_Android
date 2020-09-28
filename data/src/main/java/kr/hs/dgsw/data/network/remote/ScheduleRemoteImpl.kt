package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseRemote
import kr.hs.dgsw.data.dto.response.ScheduleResponse
import kr.hs.dgsw.data.entity.ScheduleData
import kr.hs.dgsw.data.network.api.ScheduleApi
import javax.inject.Inject

class ScheduleRemoteImpl @Inject constructor(
    override val api: ScheduleApi
) : BaseRemote<ScheduleApi>(), ScheduleRemote {
    override fun getPresetScheduleList(): Single<List<ScheduleData>> =
        api.getPresetScheduleList().map(getResponse())
            .map(ScheduleResponse::schedules)
}