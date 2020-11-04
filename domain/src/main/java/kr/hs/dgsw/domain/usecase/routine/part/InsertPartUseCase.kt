package kr.hs.dgsw.domain.usecase.routine.part

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.e.ColorEnum
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertPartUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<InsertPartUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
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
        val color: ColorEnum,
        val speed: Double,
        val incline: Int
    )
}