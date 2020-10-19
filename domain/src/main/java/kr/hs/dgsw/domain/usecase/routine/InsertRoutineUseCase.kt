package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<InsertRoutineUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        return routineRepository.insertRoutine(params.routine)
    }

    data class Params(
        val routine: Routine
    )
}