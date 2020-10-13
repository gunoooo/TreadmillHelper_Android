package kr.hs.dgsw.treadmill_helper.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import javax.inject.Inject

open class TimerViewModelFactory @Inject constructor(
    private val getRoutineUseCase: GetRoutineUseCase,
    private val countDownTimeUseCase: CountDownTimeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetRoutineUseCase::class.java,
            CountDownTimeUseCase::class.java
        ).newInstance(
            getRoutineUseCase,
            countDownTimeUseCase
        )
    }
}