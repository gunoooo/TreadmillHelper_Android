package kr.hs.dgsw.treadmill_helper.ui.routine.part.add

import kr.hs.dgsw.domain.usecase.routine.part.InsertPartUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class PartAddViewModelFactory @Inject constructor(
    insertPartUseCase: InsertPartUseCase
) : BaseViewModelFactory(
    insertPartUseCase
)