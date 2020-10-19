package kr.hs.dgsw.treadmill_helper.ui.timer

import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class TimerViewModelFactory @Inject constructor(
    getRoutineUseCase: GetRoutineUseCase,
    countDownTimeUseCase: CountDownTimeUseCase
) : BaseViewModelFactory(
    getRoutineUseCase,
    countDownTimeUseCase
)