package kr.hs.dgsw.treadmill_helper.ui.main

import android.util.Log
import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.domain.callback.TimerCallback
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.usecase.ScheduleUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel

class MainViewModel(
    private val scheduleUseCase: ScheduleUseCase
) : BaseViewModel() {

    fun startTimer(idx: Int) {
        addDisposable(scheduleUseCase.buildUseCaseObservable(
            ScheduleUseCase.Params(
                idx,
                object : TimerCallback {
                    override fun onRun(second: Int) {
                        Log.d("TIMER - ", "SECOND : $second")
                    }

                    override fun onPart(part: Part) {
                        Log.d("TIMER - ", "PART : ${part.time}")
                    }

                    override fun call() {
                        Log.d("TIMER - ","END")
                    }
                }
            )
        ), object : DisposableCompletableObserver() {
            override fun onComplete() {
                Log.d("TIMER - ","SUCCESSFUL FINISHED")
            }

            override fun onError(e: Throwable) {
                Log.e("TIMER - ","FAIL")
            }
        })
    }
}