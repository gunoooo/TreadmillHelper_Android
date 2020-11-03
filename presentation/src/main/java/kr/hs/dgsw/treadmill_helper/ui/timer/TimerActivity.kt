package kr.hs.dgsw.treadmill_helper.ui.timer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_timer.*
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
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
        initUI()
        initIntent()
    }

    private fun initUI() {
        initRecyclerView()
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
    }

    private fun initIntent() {
        val routine = intent.getSerializableExtra(EXTRA_ROUTINE) as Routine
        mViewModel.setRoutine(routine)
    }

    companion object {
        const val EXTRA_ROUTINE = "routine"
    }
}
