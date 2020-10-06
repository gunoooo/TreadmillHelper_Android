package kr.hs.dgsw.treadmill_helper.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityMainBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.listener.SnapPagerScrollListener
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
                part_recyclerview.smoothScrollToPosition(partIndex)
            })

            remainingTimeData.observe(this@MainActivity, Observer {
                timer_view.updateProgress(it)
            })

            scheduleData.observe(this@MainActivity, Observer {
                timer_view.initSchedule(it)
            })

            videoData.observe(this@MainActivity, Observer {
                youtube_player_view.play(it.source)
            })

            timerPauseEvent.observe(this@MainActivity, Observer {
                timer_view.pause()
            })

            timerPlayEvent.observe(this@MainActivity, Observer {
                timer_view.play()
            })

            with(partListAdapter) {
                plus30secEvent.observe(this@MainActivity, Observer {
                    mViewModel.plus30sec()
                })

                minus30secEvent.observe(this@MainActivity, Observer {
                    mViewModel.minus30sec()
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setSchedule(1)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val pagerSnapHelper = PagerSnapHelper()
        part_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        part_recyclerview.setHasFixedSize(true)
        part_recyclerview.addOnScrollListener(
            SnapPagerScrollListener(
                pagerSnapHelper
            ) { position ->
                mViewModel.setPart(position)
            }
        )
        pagerSnapHelper.attachToRecyclerView(part_recyclerview)
    }
}
