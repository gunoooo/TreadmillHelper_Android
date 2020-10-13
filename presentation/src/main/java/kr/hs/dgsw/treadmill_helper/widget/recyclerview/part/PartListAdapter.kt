package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_part.view.*
import kr.hs.dgsw.domain.entity.workout.Part
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemPartBinding
import kr.hs.dgsw.treadmill_helper.databinding.ItemPartVerticalBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.dpToPx

class PartListAdapter(
    private val partList: List<Part>,
    private val viewType: ViewType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    PartNavigator {
    val plus30secEvent = SingleLiveEvent<Unit>()
    override fun plus30sec() {
        plus30secEvent.call()
    }
    val minus30secEvent = SingleLiveEvent<Unit>()
    override fun minus30sec() {
        minus30secEvent.call()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (this.viewType == ViewType.HORIZONTAL)
            PartItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_part,
                    parent, false
                )
            )
        else
            PartVerticalItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_part_vertical,
                    parent, false
                )
            )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewType == ViewType.HORIZONTAL) {
            viewHolder.itemView.layoutParams.width =
                Resources.getSystem().displayMetrics.widthPixels - 16.dpToPx() * 4
            if (position == 0)
                viewHolder.itemView.stroke_1.visibility = View.INVISIBLE
            else if (position == itemCount - 1)
                viewHolder.itemView.stroke_2.visibility = View.INVISIBLE
            (viewHolder as PartItemViewHolder).bind(partList[position])
        } else {
            (viewHolder as PartVerticalItemViewHolder).bind(partList[position])
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }

    inner class PartItemViewHolder(private val binding: ItemPartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PartItemViewModel()

        fun bind(model: Part) {
            viewModel.bind(model)
            viewModel.setNavigator(this@PartListAdapter)
            binding.viewModel = viewModel
        }
    }

    inner class PartVerticalItemViewHolder(private val binding: ItemPartVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PartItemViewModel()

        fun bind(model: Part) {
            viewModel.bind(model)
            viewModel.setNavigator(this@PartListAdapter)
            binding.viewModel = viewModel
        }
    }

    enum class ViewType {
        HORIZONTAL,
        VERTICAL
    }
}