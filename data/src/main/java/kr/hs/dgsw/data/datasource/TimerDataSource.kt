package kr.hs.dgsw.data.datasource

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

open class TimerDataSource {
    fun countTime(partTime: Long) : Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
            .take(partTime)
            .map { it + 1 }
    }
}