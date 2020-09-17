package kr.hs.dgsw.domain.repository

import io.reactivex.Completable
import kr.hs.dgsw.domain.callback.TimerCallback

interface TimerRepository {
    fun schedule(scheduleIdx: Int, timerCallback: TimerCallback): Completable
}