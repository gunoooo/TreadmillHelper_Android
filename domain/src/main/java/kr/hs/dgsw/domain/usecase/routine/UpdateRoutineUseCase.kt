package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class UpdateRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<UpdateRoutineUseCase.Params, Single<Routine>>() {
    override fun buildUseCaseObservable(params: Params): Single<Routine> {
        return routineRepository.updateRoutine(params.routine)
    }

    data class Params(
        val routine: Routine
    )
}