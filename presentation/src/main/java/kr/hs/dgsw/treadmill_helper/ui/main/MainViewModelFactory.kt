package kr.hs.dgsw.treadmill_helper.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.domain.usecase.ScheduleUseCase
import javax.inject.Inject

open class MainViewModelFactory @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ScheduleUseCase::class.java
        ).newInstance(scheduleUseCase)
    }
}