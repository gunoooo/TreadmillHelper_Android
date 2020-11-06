package kr.hs.dgsw.treadmill_helper.ui.routine.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_routine_detail.*
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityRoutineDetailBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.ui.routine.part.add.PartAddDialog
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity
import javax.inject.Inject


class RoutineDetailActivity : BaseActivity<ActivityRoutineDetailBinding, RoutineDetailViewModel>() {
    @Inject
    lateinit var viewModelFactory: RoutineDetailViewModelFactory

    override val viewModel: RoutineDetailViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            with(partModifyListAdapter) {
                updateOrderEvent.observe(this@RoutineDetailActivity, Observer {
                    updatePartList()
                })
            }

            with(videoModifyListAdapter) {
                updateOrderEvent.observe(this@RoutineDetailActivity, Observer {
                    updatePartList()
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        initOnClickEvent()
    }

    private fun initIntent() {
        val routine = intent.getSerializableExtra(TimerActivity.EXTRA_ROUTINE) as Routine
        mViewModel.setRoutine(routine)
    }

    private fun initOnClickEvent() {
        back_btn.setOnClickListener {
            finish()
        }
        part_add_btn.setOnClickListener {
            val partAddDialog =
                PartAddDialog(mViewModel.routineData.value!!.idx)
            partAddDialog.successEvent.observe(this, Observer {
                (mViewModel.routineData.value!!.partList as ArrayList).add(it)
                mViewModel.partList.add(it)
                mViewModel.partModifyListAdapter
                    .notifyItemInserted(mViewModel.partList.size)
            })
            partAddDialog.show(supportFragmentManager)
        }
        related_video_add_btn.setOnClickListener {

        }
        initRecyclerViewOnClickEvent(
            part_recycler_view,
            parts_linear_layout,
            part_arrow_image_view
        )
        initRecyclerViewOnClickEvent(
            relation_video_recycler_view,
            relation_videos_linear_layout,
            relation_video_arrow_image_view
        )
    }

    private fun initRecyclerViewOnClickEvent(recyclerView: RecyclerView, button: LinearLayout, arrow: ImageView) {
        recyclerView.alpha = 1F
        button.setOnClickListener {
            arrow.animate()
                .rotationX((arrow.rotationX + 180) % 360)
                .setDuration(500)
                .start()
            if (recyclerView.visibility == View.GONE) {
                recyclerView.visibility = View.VISIBLE
                recyclerView.animate()
                    .alpha(1F)
                    .setDuration(500)
                    .start()
            } else {
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

    companion object {
        const val EXTRA_ROUTINE = "routine"
    }
}