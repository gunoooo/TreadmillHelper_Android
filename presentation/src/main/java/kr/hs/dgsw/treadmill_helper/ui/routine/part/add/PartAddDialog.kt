package kr.hs.dgsw.treadmill_helper.ui.routine.part.add

import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogPartAddBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class PartAddDialog : BaseDialog<DialogPartAddBinding, PartAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: PartAddViewModelFactory

    override val viewModel: PartAddViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {

    }
}