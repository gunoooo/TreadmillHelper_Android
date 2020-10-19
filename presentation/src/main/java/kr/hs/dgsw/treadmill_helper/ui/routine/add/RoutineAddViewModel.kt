package kr.hs.dgsw.treadmill_helper.ui.routine.add

import kr.hs.dgsw.domain.usecase.routine.InsertRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel

class RoutineAddViewModel(
    private val insertRoutineUseCase: InsertRoutineUseCase
) : BaseViewModel() {
}