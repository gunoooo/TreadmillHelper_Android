package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseRemote
import kr.hs.dgsw.data.dto.response.RoutineResponse
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.data.network.api.RoutineApi
import javax.inject.Inject

class RoutineRemoteImpl @Inject constructor(
    override val api: RoutineApi
) : BaseRemote<RoutineApi>(), RoutineRemote {
    override fun getPresetRoutineList(): Single<List<RoutineData>> =
        api.getPresetRoutineList()
            .map(getResponse())
            .map { it.routines }
}