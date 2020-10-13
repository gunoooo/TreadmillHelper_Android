package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.workout.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class UpdateRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<UpdateRoutineUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        return routineRepository.updateRoutine(params.routine)
    }

    data class Params(
        val routine: Routine
    )
}