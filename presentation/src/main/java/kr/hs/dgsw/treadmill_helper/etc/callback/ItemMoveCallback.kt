package kr.hs.dgsw.treadmill_helper.etc.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify.VideoModifyListAdapter.VideoModifyItemViewHolder
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

@Suppress("UNCHECKED_CAST")
class ItemMoveCallback<VH: RecyclerView.ViewHolder>(
    private val mAdapter: ItemTouchHelperListener<VH>
) : ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled(): Boolean = false

    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) { }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mAdapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSelectedChanged(
        viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            mAdapter.onRowSelected(viewHolder as VH)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        mAdapter.onRowClear(viewHolder as VH)
    }

    interface ItemTouchHelperListener<VH> {
        fun onRowMoved(fromPosition: Int, toPosition: Int)
        fun onRowSelected(holder: VH)
        fun onRowClear(holder: VH)
    }
}