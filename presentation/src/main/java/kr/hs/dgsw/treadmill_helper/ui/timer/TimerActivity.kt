package kr.hs.dgsw.treadmill_helper.ui.timer

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import kotlinx.android.synthetic.main.activity_timer.*
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityTimerBinding
import kr.hs.dgsw.treadmill_helper.etc.FullScreenHelper
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.listener.SnapPagerScrollListener
import javax.inject.Inject


class TimerActivity : BaseActivity<ActivityTimerBinding, TimerViewModel>() {
    @Inject
    lateinit var viewModelFactory: TimerViewModelFactory

    override val viewModel: TimerViewModel
        get() = getViewModel(viewModelFactory)

    private val fullScreenHelper = FullScreenHelper(this)

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        with(mViewModel) {
            partData.observe(this@TimerActivity, Observer {
                timer_view.updatePart(it, partIndex)
                part_recycler_view.smoothScrollToPosition(partIndex)
            })

            remainingTimeData.observe(this@TimerActivity, Observer {
                timer_view.updateProgress(it)
            })

            routineData.observe(this@TimerActivity, Observer {
                timer_view.initRoutine(it)
            })

            videoData.observe(this@TimerActivity, Observer {
                youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(it.source, 0F)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                    }
                })
            })

            pauseTimerEvent.observe(this@TimerActivity, Observer {
                timer_view.pause()
            })

            playTimerEvent.observe(this@TimerActivity, Observer {
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
        initUI()
        initIntent()
    }

    override fun onBackPressed() {
        if (youtube_player_view.isFullScreen())
            youtube_player_view.exitFullScreen()
        else
            super.onBackPressed()
    }

    private fun initUI() {
        initRecyclerView()
        initYoutubePlayerView()
        initMotionLayout()
    }

    private fun initRecyclerView() {
        val pagerSnapHelper = PagerSnapHelper()
        part_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        part_recycler_view.setHasFixedSize(true)
        part_recycler_view.addOnScrollListener(
            SnapPagerScrollListener(
                pagerSnapHelper
            ) { position ->
                mViewModel.setPart(position)
            }
        )
        pagerSnapHelper.attachToRecyclerView(part_recycler_view)
    }

    private fun initYoutubePlayerView() {
        youtube_player_view
            .getPlayerUiController().showUi(false)

        youtube_player_view.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                enterFullScreen()
            }

            override fun onYouTubePlayerExitFullScreen() {
                exitFullScreen()
            }
        })
    }

    private fun enterFullScreen() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        fullScreenHelper.enterFullScreen()
        youtube_player_view_container.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        youtube_player_view_container.requestLayout()
        motion_layout.isInteractionEnabled = false
    }

    private fun exitFullScreen() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        fullScreenHelper.exitFullScreen()
        motion_layout.isInteractionEnabled = true
    }

    @SuppressLint("SetTextI18n")
    private fun initMotionLayout() {
        motion_layout.apply {
            setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {
                    if (progress < 1)
                        setVideoPreview()
                }

                override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) { }

                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                    if (progress < 1)
                        setVideoPreview()
                    else
                        setVideoFull()
                }

                override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) { }
            })
        }
    }

    private fun setVideoPreview() {
        mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.PREVIEW
        youtube_player_view
            .getPlayerUiController().showUi(false)
    }

    private fun setVideoFull() {
        mViewModel.videoContainerViewState = TimerViewModel.VideoContainerViewState.FULL
        youtube_player_view
            .getPlayerUiController().showUi(true)
        youtube_player_view_container.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun initIntent() {
        val routine = intent.getSerializableExtra(EXTRA_ROUTINE) as Routine
        mViewModel.setRoutine(routine)
    }

    companion object {
        const val EXTRA_ROUTINE = "routine"
    }
}
