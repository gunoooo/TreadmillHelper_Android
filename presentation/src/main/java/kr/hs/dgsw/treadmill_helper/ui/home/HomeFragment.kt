package kr.hs.dgsw.treadmill_helper.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.base.BaseFragment
import kr.hs.dgsw.treadmill_helper.databinding.FragmentHomeBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override val viewModel: HomeViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            openTimerActivityEvent.observe(this@HomeFragment, Observer {
                startActivity(
                    Intent(
                        this@HomeFragment.context!!.applicationContext,
                        TimerActivity::class.java
                    ).putExtra(
                        TimerActivity.EXTRA_SCHEDULE,
                        routineData.value
                    )
                )
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerViewSlideEvent(
            part_recycler_view,
            parts_linear_layout,
            part_arrow_image_view
        )
        initRecyclerViewSlideEvent(
            relation_video_recycler_view,
            relation_videos_linear_layout,
            relation_video_arrow_image_view
        )
    }

    private fun initRecyclerViewSlideEvent(recyclerView: RecyclerView, button: LinearLayout, arrow: ImageView) {
        recyclerView.alpha = 0F
        button.setOnClickListener {
            arrow.animate()
                .rotationX((arrow.rotationX + 180) % 360)
                .setDuration(500)
                .start()
            if (recyclerView.visibility == View.GONE) {
                recyclerView.visibility = View.VISIBLE
                root_view.post {
                    root_view
                        .smoothScrollTo(0, recyclerView.bottom)
                }
                recyclerView.animate()
                    .alpha(1F)
                    .setDuration(500)
                    .start()
            } else {
                root_view.post {
                    root_view
                        .smoothScrollTo(0, recyclerView.top)
                }
                recyclerView.animate()
                    .alpha(0F)
                    .setDuration(500)
                    .withEndAction {
                        recyclerView.visibility = View.GONE
                    }
                    .start()
            }
        }
    }
}