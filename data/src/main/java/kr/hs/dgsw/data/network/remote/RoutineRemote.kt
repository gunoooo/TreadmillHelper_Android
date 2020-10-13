package kr.hs.dgsw.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.data.entity.RoutineData

interface RoutineRemote {
    fun getPresetRoutineList(): Single<List<RoutineData>>
}