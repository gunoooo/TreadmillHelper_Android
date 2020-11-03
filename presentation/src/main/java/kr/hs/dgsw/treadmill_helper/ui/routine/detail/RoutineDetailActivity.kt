package kr.hs.dgsw.treadmill_helper.ui.routine.detail

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_routine_detail.*
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityRoutineDetailBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject


class RoutineDetailActivity : BaseActivity<ActivityRoutineDetailBinding, RoutineDetailViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineDetailViewModelFactory

    override val viewModel: RoutineDetailViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {

    }

    companion object {
        const val EXTRA_ROUTINE = "routine"
    }
}