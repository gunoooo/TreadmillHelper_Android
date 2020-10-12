package kr.hs.dgsw.treadmill_helper.ui.timer

import androidx.lifecycle.MutableLiveData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.domain.entity.schedule.Schedule
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.schedule.GetScheduleUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.toMilliseconds
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.PartListAdapter
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.VideoListAdapter

class TimerViewModel(
    private val getScheduleUseCase: GetScheduleUseCase,
    private val countDownTimeUseCase: CountDownTimeUseCase
) : BaseViewModel() {
    private lateinit var timer: Disposable
    private val partList = ArrayList<Part>()
    private val videoList = ArrayList<Video>()

    var partIndex = 0
    var videoState = PlayerConstants.PlayerState.UNSTARTED
    var videoContainerViewState = VideoContainerViewState.HIDE

    val partListAdapter = PartListAdapter(partList)
    val videoListAdapter = VideoListAdapter(videoList)

    val scheduleData = MutableLiveData<Schedule>()
    val partData = MutableLiveData<Part>()
    val videoData = MutableLiveData<Video>()
    val remainingTimeData = MutableLiveData<Int>()

    val timerPauseEvent = SingleLiveEvent<Unit>()
    val timerPlayEvent = SingleLiveEvent<Unit>()

    init {
        setSchedule(1)
    }

    fun setSchedule(scheduleIdx: Int) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(scheduleIdx)),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    scheduleData.value = t
                    setPart()
                    setVideo()
                    partList.refreshAll(scheduleData.value!!.partList)
                    partListAdapter.notifyDataSetChanged()
                    videoList.refreshAll(scheduleData.value!!.relatedVideoList)
                    videoListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            })
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
                    onErrorEvent.value = e
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
        partData.value = scheduleData.value!!.partList[partIndex]
        startTimer(partData.value!!.time.toMilliseconds())
    }

    fun setVideo() {
        videoData.value =
            if (scheduleData.value!!.relatedVideoList.isEmpty())
                null
            else
                scheduleData.value!!.relatedVideoList[0]
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