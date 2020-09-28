package kr.hs.dgsw.treadmill_helper.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.domain.usecase.schedule.GetScheduleUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import javax.inject.Inject

open class MainViewModelFactory @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
    private val countDownTimeUseCase: CountDownTimeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetScheduleUseCase::class.java,
            CountDownTimeUseCase::class.java
        ).newInstance(
            getScheduleUseCase,
            countDownTimeUseCase
        )
    }
}