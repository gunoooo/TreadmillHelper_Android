package kr.hs.dgsw.treadmill_helper.ui.routine.detail

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.usecase.routine.UpdateRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.callback.ItemMoveCallback
import kr.hs.dgsw.treadmill_helper.etc.listener.StartDragListener
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.modify.PartModifyListAdapter
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify.VideoModifyListAdapter

class RoutineDetailViewModel(
    private val updateRoutineUseCase: UpdateRoutineUseCase
) : BaseViewModel(),
    StartDragListener {
    private val partList = ArrayList<Part>()
    private val relatedVideoList = ArrayList<RelatedVideo>()

    val partModifyListAdapter =
        PartModifyListAdapter(
            partList,
            this
        )
    val partModifyTouchHelper =
        ItemTouchHelper(
            ItemMoveCallback(
                partModifyListAdapter
            )
        )

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

    val routineData = MutableLiveData<Routine>()

    fun setRoutine(routine: Routine) {
        routineData.value = routine
        partList.refreshAll(routine.partList)
        partModifyListAdapter.notifyDataSetChanged()
        relatedVideoList.refreshAll(routine.relatedVideoList)
        videoModifyListAdapter.notifyDataSetChanged()
    }

    fun updatePartList() {
        addDisposable(updateRoutineUseCase.buildUseCaseObservable(
            UpdateRoutineUseCase.Params(
                idx = routineData.value!!.idx,
                title = routineData.value!!.title,
                routineType = routineData.value!!.routineType,
                partList = partList,
                relatedVideoList = relatedVideoList
            )
        ), object : DisposableSingleObserver<Routine>() {
            override fun onSuccess(t: Routine) {
                routineData.value = t
            }

            override fun onError(e: Throwable) {
                errorEvent.value = e
            }
        })
    }

    override fun requestDrag(holder: RecyclerView.ViewHolder) {
        if (holder is VideoModifyListAdapter.VideoModifyItemViewHolder)
            videoModifyTouchHelper.startDrag(holder)
        else if (holder is PartModifyListAdapter.PartModifyItemViewHolder)
            partModifyTouchHelper.startDrag(holder)
    }
}