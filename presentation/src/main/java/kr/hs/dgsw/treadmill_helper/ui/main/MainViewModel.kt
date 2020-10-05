package kr.hs.dgsw.treadmill_helper.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.domain.entity.schedule.Schedule
import kr.hs.dgsw.domain.usecase.schedule.GetScheduleUseCase
import kr.hs.dgsw.domain.usecase.timer.CountDownTimeUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.toMilliseconds
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.PartListAdapter

class MainViewModel(
    private val getScheduleUseCase: GetScheduleUseCase,
    private val countDownTimeUseCase: CountDownTimeUseCase
) : BaseViewModel() {
    var partIndex: Int = 0
    private lateinit var timer: Disposable
    private val partList = ArrayList<Part>()

    val partListAdapter = PartListAdapter(partList)

    val scheduleData = MutableLiveData<Schedule>()
    val partData = MutableLiveData<Part>()
    val remainingTimeData = MutableLiveData<Int>()

    val timerPauseEvent = SingleLiveEvent<Unit>()
    val timerPlayEvent = SingleLiveEvent<Unit>()

    fun setSchedule(scheduleIdx: Int) {
        addDisposable(getScheduleUseCase.buildUseCaseObservable(GetScheduleUseCase.Params(scheduleIdx)),
            object : DisposableSingleObserver<Schedule>() {
                override fun onSuccess(t: Schedule) {
                    scheduleData.value = t
                    setPart()
                    partList.refreshAll(scheduleData.value!!.partList)
                    partListAdapter.notifyDataSetChanged()
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
                    if (++partIndex == scheduleData.value!!.partList.size) {

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
}