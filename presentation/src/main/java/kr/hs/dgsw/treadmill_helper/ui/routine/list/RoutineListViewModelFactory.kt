package kr.hs.dgsw.treadmill_helper.ui.routine.list

import kr.hs.dgsw.domain.usecase.routine.GetRoutineListUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class RoutineListViewModelFactory @Inject constructor(
    getRoutineListUseCase: GetRoutineListUseCase
) : BaseViewModelFactory(
    getRoutineListUseCase
)