package kr.hs.dgsw.treadmill_helper.ui.routine.part.add

import kr.hs.dgsw.domain.usecase.routine.part.InsertPartUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel

class PartAddViewModel(
    private val insertPartUseCase: InsertPartUseCase
) : BaseViewModel() {

}