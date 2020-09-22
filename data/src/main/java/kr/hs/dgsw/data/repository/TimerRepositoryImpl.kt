package kr.hs.dgsw.data.repository

import io.reactivex.Observable
import kr.hs.dgsw.domain.repository.TimerRepository
import java.util.concurrent.TimeUnit

class TimerRepositoryImpl : TimerRepository {
    override fun countDownTime(partTime: Long): Observable<Int> {
        return Observable.interval(1, TimeUnit.SECONDS)
            .take(partTime)
            .map { it + 1 }
            .map { it.toInt() } // long to int
            .map { partTime.toInt() + 1 - it } // reverse
    }
}