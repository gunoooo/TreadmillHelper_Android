package kr.hs.dgsw.treadmill_helper.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityMainBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.format
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override val viewModel: MainViewModel
        get() = getViewModel(viewModelFactory)

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        with(mViewModel) {
            partData.observe(this@MainActivity, Observer {
                timer_view.updatePart(it, partIndex)
            })

            remainingTimeData.observe(this@MainActivity, Observer {
                timer_view.updateProgress(it)
            })

            scheduleData.observe(this@MainActivity, Observer {
                timer_view.initSchedule(it)
            })

            timerPauseEvent.observe(this@MainActivity, Observer {
                timer_view.pause()
            })

            timerPlayEvent.observe(this@MainActivity, Observer {
                timer_view.play()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setSchedule(1)
    }
}
