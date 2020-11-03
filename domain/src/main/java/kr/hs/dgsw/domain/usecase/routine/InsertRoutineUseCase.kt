package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.e.RoutineTypeEnum
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<InsertRoutineUseCase.Params, Single<Routine>>() {
    override fun buildUseCaseObservable(params: Params): Single<Routine> {
        return routineRepository.insertRoutine(
            Routine(
                title = params.title,
                routineType = RoutineTypeEnum.CUSTOM,
                partList = emptyList(),
                relatedVideoList = emptyList()
            )
        )
    }

    data class Params(
        val title: String
    )
}