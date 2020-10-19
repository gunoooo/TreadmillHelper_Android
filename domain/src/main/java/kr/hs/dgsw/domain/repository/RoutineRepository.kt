package kr.hs.dgsw.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.domain.entity.routine.Routine

interface RoutineRepository {
    fun getRoutine(routineIdx: Int): Single<Routine>

    fun getRoutineList(): Single<List<Routine>>

    fun insertRoutine(routine: Routine): Completable

    fun updateRoutine(routine: Routine): Completable
}