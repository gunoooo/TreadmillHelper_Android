package kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemRoutineBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent

class RoutineListAdapter(
    private val routineList: List<Routine>
) : RecyclerView.Adapter<RoutineListAdapter.RoutineItemViewHolder>(),
    RoutineNavigator {
    val clickItemEvent = SingleLiveEvent<Routine>()
    override fun onClickItem(routine: Routine) {
        clickItemEvent.value = routine
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutineListAdapter.RoutineItemViewHolder {
        return RoutineItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_routine,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RoutineListAdapter.RoutineItemViewHolder, position: Int) {
        holder.bind(routineList[position])
    }

    override fun getItemCount(): Int {
        return routineList.size
    }

    inner class RoutineItemViewHolder(private val binding: ItemRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RoutineItemViewModel()

        fun bind(model: Routine) {
            viewModel.bind(model)
            viewModel.setNavigator(this@RoutineListAdapter)
            binding.viewModel = viewModel
        }
    }
}