package kr.hs.dgsw.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.callback.TimerCallback
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Inject

class ScheduleUseCase @Inject constructor(
    private val timerRepository: TimerRepository
) : ParamsUseCase<ScheduleUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        return timerRepository.schedule(params.scheduleIdx, params.timerCallback)
    }

    data class Params(
        val scheduleIdx: Int,
        val timerCallback: TimerCallback
    )
}