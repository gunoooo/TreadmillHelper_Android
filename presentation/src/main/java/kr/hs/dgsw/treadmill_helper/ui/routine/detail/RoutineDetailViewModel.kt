package kr.hs.dgsw.treadmill_helper.ui.routine.detail

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.usecase.routine.UpdateRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.callback.ItemMoveCallback
import kr.hs.dgsw.treadmill_helper.etc.listener.StartDragListener
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify.VideoModifyListAdapter

class RoutineDetailViewModel(
    private val updateRoutineUseCase: UpdateRoutineUseCase
) : BaseViewModel(),
    StartDragListener {
    private val relatedVideoList = ArrayList<RelatedVideo>()

    val videoModifyListAdapter =
        VideoModifyListAdapter(
            relatedVideoList,
            this
        )
    val videoModifyTouchHelper =
        ItemTouchHelper(
            ItemMoveCallback(
                videoModifyListAdapter
            )
        )

    init {
        relatedVideoList.add(
            RelatedVideo(1, "xrwOj3KnawQ", "222dd", "https://i.ytimg.com/vi/VsmsGtiavUQ/original.jpg", 320, VideoCategoryEnum.CUSTOM))
        relatedVideoList.add(
            RelatedVideo(2, "xrwOj3KnawQ", "ddd", "https://i.ytimg.com/vi/VsmsGtiavUQ/original.jpg", 320, VideoCategoryEnum.CUSTOM))
    }

    override fun requestDrag(holder: RecyclerView.ViewHolder) {
        videoModifyTouchHelper.startDrag(holder)
    }
}