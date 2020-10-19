package kr.hs.dgsw.treadmill_helper.ui.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.data.mapper.toVideo
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat
import kr.hs.dgsw.treadmill_helper.ui.routine.list.RoutineListDialog
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.PartListAdapter
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.VideoListAdapter

class HomeViewModel(
    private val getRoutineUseCase: GetRoutineUseCase
) : BaseViewModel() {
    private val partList = ArrayList<Part>()
    private val videoList = ArrayList<Video>()

    val partListAdapter = PartListAdapter(partList, PartListAdapter.ViewType.VERTICAL)
    val videoListAdapter = VideoListAdapter(videoList)

    val routineListDialog = RoutineListDialog()

    val routineData = MutableLiveData<Routine>()
    val titleData = MutableLiveData<String>()
    val timeData = MutableLiveData<String>()
    val distanceData = MutableLiveData<String>()
    val calorieData = MutableLiveData<String>()

    val openTimerActivityEvent = SingleLiveEvent<Unit>()

    fun setRoutine(routineIdx: Int) {
        addDisposable(getRoutineUseCase.buildUseCaseObservable(GetRoutineUseCase.Params(routineIdx)),
            object : DisposableSingleObserver<Routine>() {
                override fun onSuccess(t: Routine) {
                    routineData.value = t
                    titleData.value = t.title
                    distanceData.value = t.getDistance().toString()
                    calorieData.value = t.getCalorie().toString()
                    timeData.value = t.partList.sumBy { it.time }.secToTimeFormat()
                    partList.refreshAll(routineData.value!!.partList)
                    partListAdapter.notifyDataSetChanged()
                    videoList.refreshAll(routineData.value!!.relatedVideoList.map { it.toVideo() })
                    videoListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
    }

    fun onClickStart() {
        openTimerActivityEvent.call()
    }
}