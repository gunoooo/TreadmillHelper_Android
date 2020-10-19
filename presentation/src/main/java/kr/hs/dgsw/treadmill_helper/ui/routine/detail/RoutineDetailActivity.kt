package kr.hs.dgsw.treadmill_helper.ui.routine.detail

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
}