package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.workout.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class GetRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<GetRoutineUseCase.Params, Single<Routine>>() {
    override fun buildUseCaseObservable(params: Params): Single<Routine> {
        return routineRepository.getRoutine(params.routineIdx)
    }

    data class Params(
        val routineIdx: Int
    )
}