package kr.hs.dgsw.treadmill_helper.ui.routine.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_routine_list.*
import kr.hs.dgsw.data.database.SharedPreferenceManager
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseBottomSheetDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRoutineListBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.ui.routine.add.RoutineAddDialog
import javax.inject.Inject

class RoutineListDialog : BaseBottomSheetDialog<DialogRoutineListBinding, RoutineListViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineListViewModelFactory

    override val viewModel: RoutineListViewModel
        get() = getViewModel(viewModelFactory)

    val clickItemEvent = SingleLiveEvent<Int>()

    override fun observerViewModel() {
        with(mViewModel) {
            with(routineListAdapter) {
                clickItemEvent.observe(this@RoutineListDialog, Observer {
                    this@RoutineListDialog.clickItemEvent.value = it.idx
                    insertRoutineIdx(it.idx)
                    dismiss()
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

    private fun initOnClickEvent() {
        routine_add_btn.setOnClickListener {
            RoutineAddDialog().show(parentFragmentManager)
        }
    }
}