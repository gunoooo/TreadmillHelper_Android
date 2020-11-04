package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.modify

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_part_modify.view.*
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemPartModifyBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.callback.ItemMoveCallback
import kr.hs.dgsw.treadmill_helper.etc.listener.StartDragListener
import java.util.*

class PartModifyListAdapter(
    private val partList: List<Part>,
    private val startDragListener: StartDragListener
) : RecyclerView.Adapter<PartModifyListAdapter.PartModifyItemViewHolder>(),
    ItemMoveCallback.ItemTouchHelperListener<PartModifyListAdapter.PartModifyItemViewHolder>,
    PartModifyNavigator {
    val updateOrderEvent = SingleLiveEvent<Unit>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartModifyListAdapter.PartModifyItemViewHolder {
        return PartModifyItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_part_modify,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PartModifyListAdapter.PartModifyItemViewHolder,
        position: Int
    ) {
        holder.bind(partList[position])
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
        return partList.size
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(partList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(partList, i, i - 1)
            }
        }
        for ((i, part) in partList.withIndex()) {
            part.idx = i + 1
        }
        notifyItemMoved(fromPosition, toPosition)
        updateOrderEvent.call()
    }

    override fun onRowSelected(holder: PartModifyItemViewHolder) {
        holder.itemView.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(holder: PartModifyItemViewHolder) {
        holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.colorBackground))
    }

    inner class PartModifyItemViewHolder(private val binding: ItemPartModifyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PartModifyItemViewModel()

        fun bind(model: Part) {
            viewModel.bind(model)
            viewModel.setNavigator(this@PartModifyListAdapter)
            binding.viewModel = viewModel
        }
    }
}