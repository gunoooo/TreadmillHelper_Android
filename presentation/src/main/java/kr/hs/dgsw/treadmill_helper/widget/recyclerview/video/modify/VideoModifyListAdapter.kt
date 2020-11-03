package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_video_modify.view.*
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemVideoModifyBinding
import kr.hs.dgsw.treadmill_helper.etc.callback.ItemMoveCallback
import kr.hs.dgsw.treadmill_helper.etc.listener.StartDragListener
import java.util.*


class VideoModifyListAdapter(
    private val relatedVideoList: List<RelatedVideo>,
    private val startDragListener: StartDragListener
) : RecyclerView.Adapter<VideoModifyListAdapter.VideoModifyItemViewHolder>(),
    ItemMoveCallback.ItemTouchHelperListener<VideoModifyListAdapter.VideoModifyItemViewHolder>,
    VideoModifyNavigator {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoModifyListAdapter.VideoModifyItemViewHolder {
        return VideoModifyItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video_modify,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: VideoModifyListAdapter.VideoModifyItemViewHolder,
        position: Int
    ) {
        holder.bind(relatedVideoList[position])
        holder.itemView.drag_btn.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    startDragListener.requestDrag(holder)
                }
                return false
            }
        })
    }

    override fun getItemCount(): Int {
        return relatedVideoList.size
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(relatedVideoList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(relatedVideoList, i, i - 1)
            }
        }
        for ((i, relatedVideo) in relatedVideoList.withIndex()) {
            relatedVideo.idx = i + 1
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(holder: VideoModifyItemViewHolder) {
        holder.itemView.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(holder: VideoModifyItemViewHolder) {
        holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.colorBackground))
    }

    inner class VideoModifyItemViewHolder(private val binding: ItemVideoModifyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = VideoModifyItemViewModel()

        fun bind(model: RelatedVideo) {
            viewModel.bind(model)
            viewModel.setNavigator(this@VideoModifyListAdapter)
            binding.viewModel = viewModel
        }
    }
}