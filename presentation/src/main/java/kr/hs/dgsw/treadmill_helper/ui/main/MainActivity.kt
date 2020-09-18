package kr.hs.dgsw.treadmill_helper.ui.main

import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override val viewModel: MainViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
    }
}
