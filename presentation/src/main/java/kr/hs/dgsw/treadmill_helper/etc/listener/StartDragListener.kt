package kr.hs.dgsw.treadmill_helper.etc.listener

import androidx.recyclerview.widget.RecyclerView

interface StartDragListener {
    fun requestDrag(holder: RecyclerView.ViewHolder)
}