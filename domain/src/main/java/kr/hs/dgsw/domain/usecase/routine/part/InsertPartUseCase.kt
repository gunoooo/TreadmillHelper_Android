package kr.hs.dgsw.domain.usecase.routine.part

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertPartUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<InsertPartUseCase.Params, Single<Part>>() {
    override fun buildUseCaseObservable(params: Params): Single<Part> {
        return routineRepository.insertPart(
            routineIdx = params.routineIdx,
            part = Part(
                idx = 0,
                title = params.title,
                time = params.time,
                color = params.color,
                speed = params.speed,
                incline = params.incline
            )
        )
    }

    data class Params(
        val routineIdx: Int,
        val title: String,
        var time: Int,
        val color: String,
        val speed: Double,
        val incline: Int
    )
}