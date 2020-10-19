package kr.hs.dgsw.treadmill_helper.ui.routine.detail

import kr.hs.dgsw.domain.usecase.routine.UpdateRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class RoutineDetailViewModelFactory @Inject constructor(
    updateRoutineUseCase: UpdateRoutineUseCase
) : BaseViewModelFactory(
    updateRoutineUseCase
)