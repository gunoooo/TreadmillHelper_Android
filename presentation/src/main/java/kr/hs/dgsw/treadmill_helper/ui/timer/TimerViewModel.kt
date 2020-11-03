package kr.hs.dgsw.treadmill_helper.ui.timer

import androidx.lifecycle.MutableLiveData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.data.mapper.toVideo
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.routine.GetRoutineUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.toMilliseconds
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.PartListAdapter
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.VideoListAdapter

class TimerViewModel(
    private val getRoutineUseCase: GetRoutineUseCase,
    private val countDownTimeUseCase: CountDownTimeUseCase
) : BaseViewModel() {
    private lateinit var timer: Disposable
    private val partList = ArrayList<Part>()
    private val videoList = ArrayList<Video>()

    var partIndex = 0
    var videoState = PlayerConstants.PlayerState.UNSTARTED
    var videoContainerViewState = VideoContainerViewState.HIDE

    val partListAdapter = PartListAdapter(partList, PartListAdapter.ViewType.HORIZONTAL)
    val videoListAdapter = VideoListAdapter(videoList)

    val routineData = MutableLiveData<Routine>()
    val partData = MutableLiveData<Part>()
    val videoData = MutableLiveData<Video>()
    val remainingTimeData = MutableLiveData<Int>()

    val timerPauseEvent = SingleLiveEvent<Unit>()
    val timerPlayEvent = SingleLiveEvent<Unit>()

    fun setRoutine(routine: Routine) {
        routineData.value = routine
        setPart()
        setVideo()
        partList.refreshAll(routineData.value!!.partList)
        partListAdapter.notifyDataSetChanged()
        videoList.refreshAll(routineData.value!!.relatedVideoList.map { it.toVideo() })
        videoListAdapter.notifyDataSetChanged()
    }

    private fun startTimer(partTime: Int) {
        timer = addDisposable(
            countDownTimeUseCase.buildUseCaseObservable(
                CountDownTimeUseCase.Params(partTime)
            ),
            object : DisposableObserver<Int>() {
                override fun onNext(t: Int) {
                    remainingTimeData.value = t
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e
                }

                override fun onComplete() {
                    if (++partIndex > partList.size - 1) {

                    } else {
                        setPart()
                    }
                }
            }
        )
    }

    fun setPart(position: Int) {
        partIndex = position
        timer.dispose()
        setPart()
        timerPlayEvent.call()
    }

    private fun setPart() {
        partData.value = routineData.value!!.partList[partIndex]
        startTimer(partData.value!!.time.toMilliseconds())
    }

    fun setVideo() {
        videoData.value =
            if (routineData.value!!.relatedVideoList.isEmpty())
                null
            else
                routineData.value!!.relatedVideoList[0].toVideo()
    }

    fun plus30sec() {
        timer.dispose()
        startTimer(remainingTimeData.value!! + 30000 - 1000)
    }

    fun minus30sec() {
        timer.dispose()
        if (remainingTimeData.value!! - 30000 - 1000 > 0) {
            startTimer(remainingTimeData.value!! - 30000 - 1000)
        }
        else {
            if (++partIndex > partList.size - 1) {

            } else {
                setPart()
            }
        }
    }

    fun onClickTimer() {
        if (timer.isDisposed) {
            timerPlayEvent.call()
            startTimer(remainingTimeData.value!! - 1000)
        }
        else {
            timerPauseEvent.call()
            timer.dispose()
        }
    }

    enum class VideoContainerViewState {
        HIDE, PREVIEW, FULL,
    }
}