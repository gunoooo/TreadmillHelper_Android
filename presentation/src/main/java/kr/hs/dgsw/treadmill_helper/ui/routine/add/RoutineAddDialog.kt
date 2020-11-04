package kr.hs.dgsw.treadmill_helper.ui.routine.add

import androidx.lifecycle.Observer
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRoutineAddBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class RoutineAddDialog : BaseDialog<DialogRoutineAddBinding, RoutineAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineAddViewModelFactory

    override val viewModel: RoutineAddViewModel
        get() = getViewModel(viewModelFactory)

    val openRoutineDetailViewEvent = SingleLiveEvent<Routine>()

    override fun observerViewModel() {
        with(mViewModel) {
            successEvent.observe(this@RoutineAddDialog, Observer {
                openRoutineDetailViewEvent.value = it
                dismiss()
            })
        }
    }
}