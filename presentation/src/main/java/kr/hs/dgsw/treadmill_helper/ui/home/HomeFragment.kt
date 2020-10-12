package kr.hs.dgsw.treadmill_helper.ui.home

import androidx.lifecycle.Observer
import kr.hs.dgsw.treadmill_helper.base.BaseFragment
import kr.hs.dgsw.treadmill_helper.databinding.FragmentHomeBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.startActivity
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel
        get() = getViewModel()

    override fun observerViewModel() {
        with(mViewModel) {
            openTimerActivityEvent.observe(this@HomeFragment, Observer {
                startActivity(TimerActivity::class.java)
            })
        }
    }
}