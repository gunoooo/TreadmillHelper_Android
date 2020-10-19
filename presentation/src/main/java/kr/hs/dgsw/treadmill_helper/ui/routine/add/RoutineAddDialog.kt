package kr.hs.dgsw.treadmill_helper.ui.routine.add

import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRoutineAddBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class RoutineAddDialog : BaseDialog<DialogRoutineAddBinding, RoutineAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineAddViewModelFactory

    override val viewModel: RoutineAddViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
    }
}