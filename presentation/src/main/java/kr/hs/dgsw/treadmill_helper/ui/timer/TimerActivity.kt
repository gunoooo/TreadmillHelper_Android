package kr.hs.dgsw.treadmill_helper.ui.timer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_timer.*
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityMainBinding
import kr.hs.dgsw.treadmill_helper.databinding.ActivityTimerBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.listener.SnapPagerScrollListener
import javax.inject.Inject


class TimerActivity : BaseActivity<ActivityTimerBinding, TimerViewModel>() {
    @Inject
    lateinit var viewModelFactory: TimerViewModelFactory

    override val viewModel: TimerViewModel
        get() = getViewModel(viewModelFactory)

    private lateinit var youTubePlayer: YouTubePlayer

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        with(mViewModel) {
            partData.observe(this@TimerActivity, Observer {
                timer_view.updatePart(it, partIndex)
                part_recyclerview.smoothScrollToPosition(partIndex)
            })

            remainingTimeData.observe(this@TimerActivity, Observer {
                timer_view.updateProgress(it)
            })

            scheduleData.observe(this@TimerActivity, Observer {
                timer_view.initSchedule(it)
            })

            videoData.observe(this@TimerActivity, Observer {
                youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        this@TimerActivity.youTubePlayer = youTubePlayer
                        this@TimerActivity.youTubePlayer.loadVideo(it.source, 0F)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                    }
                })
            })

            timerPauseEvent.observe(this@TimerActivity, Observer {
                timer_view.pause()
            })

            timerPlayEvent.observe(this@TimerActivity, Observer {
                timer_view.play()
            })

            with(partListAdapter) {
                plus30secEvent.observe(this@TimerActivity, Observer {
                    mViewModel.plus30sec()
                })

                minus30secEvent.observe(this@TimerActivity, Observer {
                    mViewModel.minus30sec()
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initMotionLayout()
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

    @SuppressLint("SetTextI18n")
    private fun initMotionLayout() {
        youtube_player_view
            .getPlayerUiController().showUi(false)

        motion_layout.apply {
            setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) { }

                override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {
                    if (progress < 1) {
                        mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.PREVIEW
                        youtube_player_view
                            .getPlayerUiController().showUi(false)
                    }
                    else {
                        mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.FULL
                        youtube_player_view
                            .getPlayerUiController().showUi(true)
                    }
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) { }

                override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) { }
            })
        }

        video_control_btn.setOnClickListener {
            if (mViewModel.videoContainerViewState == TimerViewModel.VideoContainerViewState.HIDE) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(youtube_player_view_container)
                youtube_player_view_container.layoutParams =
                    ConstraintLayout.LayoutParams(0, 90)
                constraintSet.applyTo(youtube_player_view_container)
                mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.PREVIEW
                video_control_text_view.text = "CLOSE VIDEO"
            } else if (mViewModel.videoContainerViewState == TimerViewModel.VideoContainerViewState.PREVIEW) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(youtube_player_view_container)
                youtube_player_view_container.layoutParams =
                    ConstraintLayout.LayoutParams(0, 1)
                constraintSet.applyTo(youtube_player_view_container)
                mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.HIDE
                video_control_text_view.text = "OPEN VIDEO"
            }
        }
    }
}
