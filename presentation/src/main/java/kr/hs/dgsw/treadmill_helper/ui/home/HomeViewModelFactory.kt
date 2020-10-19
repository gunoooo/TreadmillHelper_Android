package kr.hs.dgsw.treadmill_helper.ui.home

import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class HomeViewModelFactory @Inject constructor(
    getRoutineUseCase: GetRoutineUseCase
) : BaseViewModelFactory(
    getRoutineUseCase
)