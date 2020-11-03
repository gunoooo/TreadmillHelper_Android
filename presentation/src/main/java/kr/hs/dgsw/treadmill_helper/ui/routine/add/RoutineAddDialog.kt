package kr.hs.dgsw.treadmill_helper.ui.routine.add

import android.content.Intent
import androidx.lifecycle.Observer
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRoutineAddBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.startActivity
import kr.hs.dgsw.treadmill_helper.ui.routine.detail.RoutineDetailActivity
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity
import javax.inject.Inject

class RoutineAddDialog : BaseDialog<DialogRoutineAddBinding, RoutineAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineAddViewModelFactory

    override val viewModel: RoutineAddViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            successEvent.observe(this@RoutineAddDialog, Observer {
                startActivity(
                    Intent(
                        this@RoutineAddDialog.context!!.applicationContext,
                        RoutineDetailActivity::class.java
                    ).putExtra(
                        RoutineDetailActivity.EXTRA_ROUTINE,
                        it
                    )
                )
            })
        }
    }
}