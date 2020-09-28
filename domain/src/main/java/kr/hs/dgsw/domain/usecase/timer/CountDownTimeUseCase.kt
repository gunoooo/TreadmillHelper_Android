package kr.hs.dgsw.domain.usecase.timer

import io.reactivex.Observable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Inject

class CountDownTimeUseCase @Inject constructor(
    private val timerRepository: TimerRepository
) : ParamsUseCase<CountDownTimeUseCase.Params, Observable<Int>>() {
    override fun buildUseCaseObservable(params: Params): Observable<Int> {
        return timerRepository.countDownTime(params.partTime)
    }

    data class Params(
        val partTime: Int
    )
}