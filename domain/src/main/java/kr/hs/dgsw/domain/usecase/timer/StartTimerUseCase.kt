package kr.hs.dgsw.domain.usecase.timer

import io.reactivex.Observable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Inject

class StartTimerUseCase @Inject constructor(
    private val timerRepository: TimerRepository
) : ParamsUseCase<StartTimerUseCase.Params, Observable<Int>>() {
    override fun buildUseCaseObservable(params: Params): Observable<Int> {
        return timerRepository.countDownTime(params.partTime)
    }

    data class Params(
        val partTime: Long
    )
}