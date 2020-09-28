package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.entity.ScheduleData

interface ScheduleRemote {
    fun getPresetScheduleList(): Single<List<ScheduleData>>
}