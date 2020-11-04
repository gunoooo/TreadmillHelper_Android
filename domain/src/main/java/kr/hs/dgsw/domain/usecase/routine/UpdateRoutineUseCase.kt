package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.e.RoutineTypeEnum
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class UpdateRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<UpdateRoutineUseCase.Params, Single<Routine>>() {
    override fun buildUseCaseObservable(params: Params): Single<Routine> {
        return routineRepository.updateRoutine(
            Routine(
                idx = params.idx,
                title = params.title,
                routineType = params.routineType,
                partList = params.partList,
                relatedVideoList = params.relatedVideoList
            )
        )
    }

    data class Params(
        val idx: Int,
        val title: String,
        val routineType: RoutineTypeEnum,
        val partList: List<Part>,
        val relatedVideoList: List<RelatedVideo>
    )
}