package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_part.view.*
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemPartBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.dpToPx

class PartListAdapter(private val partList: List<Part>) :
    RecyclerView.Adapter<PartListAdapter.PartItemViewHolder>(),
    PartNavigator {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartItemViewHolder {
        return PartItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_part,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: PartItemViewHolder, position: Int) {
        viewHolder.itemView.layoutParams.width =
            Resources.getSystem().displayMetrics.widthPixels - 16.dpToPx() * 4
        if (position == 0)
            viewHolder.itemView.stroke_1.visibility = View.INVISIBLE
        else if (position == itemCount - 1)
            viewHolder.itemView.stroke_2.visibility = View.INVISIBLE
        viewHolder.bind(partList[position])
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
}