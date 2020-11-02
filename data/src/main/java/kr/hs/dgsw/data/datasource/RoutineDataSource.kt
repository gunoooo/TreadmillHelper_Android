package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.RoutineData

interface RoutineDataSource {
    fun getRoutineList(): Single<List<RoutineData>>

    fun getRoutine(routineIdx: Int): Single<RoutineData>

    fun insertRoutineList(routineDataList: List<RoutineData>): Single<List<RoutineData>>

    fun insertRoutine(routineData: RoutineData): Single<RoutineData>

    fun deleteRoutine(routineIdx: Int): Completable
}