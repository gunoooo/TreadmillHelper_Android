package kr.hs.dgsw.treadmill_helper.ui.routine.add

import kr.hs.dgsw.domain.usecase.routine.InsertRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class RoutineAddViewModelFactory @Inject constructor(
    insertRoutineUseCase: InsertRoutineUseCase
) : BaseViewModelFactory(
    insertRoutineUseCase
)