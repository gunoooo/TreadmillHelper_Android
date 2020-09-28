package kr.hs.dgsw.data.repository

import io.reactivex.Observable
import kr.hs.dgsw.domain.repository.TimerRepository
import java.util.concurrent.TimeUnit

class TimerRepositoryImpl : TimerRepository {
    override fun countDownTime(partTime: Int): Observable<Int> {
        return Observable.interval(1, TimeUnit.MILLISECONDS)
            .take(partTime.toLong())
            .map { it + 1 }
            .map { it.toInt() } // long to int
            .map { partTime + 1000 - it } // reverse
    }
}