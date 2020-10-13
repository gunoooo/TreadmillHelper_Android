package kr.hs.dgsw.treadmill_helper.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import javax.inject.Inject

open class HomeViewModelFactory @Inject constructor(
    private val getRoutineUseCase: GetRoutineUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetRoutineUseCase::class.java
        ).newInstance(
            getRoutineUseCase
        )
    }
}