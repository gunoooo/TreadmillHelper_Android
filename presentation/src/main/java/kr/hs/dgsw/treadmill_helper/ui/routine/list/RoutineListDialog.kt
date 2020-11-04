package kr.hs.dgsw.treadmill_helper.ui.routine.list

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_routine_list.*
import kr.hs.dgsw.data.database.SharedPreferenceManager
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseBottomSheetDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRoutineListBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.ui.routine.add.RoutineAddDialog
import kr.hs.dgsw.treadmill_helper.ui.routine.detail.RoutineDetailActivity
import javax.inject.Inject

class RoutineListDialog : BaseBottomSheetDialog<DialogRoutineListBinding, RoutineListViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineListViewModelFactory

    override val viewModel: RoutineListViewModel
        get() = getViewModel(viewModelFactory)

    val dismissEvent = SingleLiveEvent<Int>()

    override fun observerViewModel() {
        with(mViewModel) {
            with(routineListAdapter) {
                clickItemEvent.observe(this@RoutineListDialog, Observer {
                    focusRoutineIdx = it.idx
                    insertRoutineIdx(focusRoutineIdx)
                    dismiss()
                })

                detailEvent.observe(this@RoutineListDialog, Observer {
                    startRoutineDetailActivity(it)
                })
            }
        }
    }

    private fun insertRoutineIdx(routineIdx: Int) {
        SharedPreferenceManager
            .insertRoutineIdx(
                context!!.applicationContext,
                routineIdx
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickEvent()
    }

    override fun onResume() {
        super.onResume()
        initFocusRoutineIdx()
        mViewModel.setRoutineList()
    }

    override fun onPause() {
        super.onPause()
        dismissEvent.value = mViewModel.focusRoutineIdx
    }

    private fun initFocusRoutineIdx() {
        if (mViewModel.focusRoutineIdx == 0) {
            mViewModel.focusRoutineIdx =
                SharedPreferenceManager.getRoutineIdx(context!!.applicationContext)
        }
        mViewModel.routineListAdapter
            .setFocusRoutineIdx(mViewModel.focusRoutineIdx)
    }

    private fun initOnClickEvent() {
        routine_add_btn.setOnClickListener {
            openRoutineAddDialog()
        }
    }

    private fun openRoutineAddDialog() {
        val routineAddDialog = RoutineAddDialog()
        routineAddDialog.show(parentFragmentManager)
        routineAddDialog.openRoutineDetailViewEvent.observe(viewLifecycleOwner,
            Observer {
                startRoutineDetailActivity(it)
            }
        )
    }

    private fun startRoutineDetailActivity(routine: Routine) {
        startActivity(
            Intent(
                this@RoutineListDialog.context!!.applicationContext,
                RoutineDetailActivity::class.java
            ).putExtra(
                RoutineDetailActivity.EXTRA_ROUTINE,
                routine
            )
        )
    }
}