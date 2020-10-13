package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.entity.RoutineData

interface RoutineDataSource {
    fun getRoutineDetailList(): Single<List<RoutineData>>

    fun getRoutineList(): Single<List<RoutineData>>

    fun getRoutine(routineIdx: Int): Single<RoutineData>

    fun insertRoutine(routineData: RoutineData): Completable

    fun updateRoutine(routineData: RoutineData): Completable
}