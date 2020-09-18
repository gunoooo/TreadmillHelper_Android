package kr.hs.dgsw.data.repository

import io.reactivex.Observable
import kr.hs.dgsw.data.datasource.TimerDataSource
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Inject

open class TimerRepositoryImpl @Inject constructor(
    private val timerDataSource: TimerDataSource
) : TimerRepository {
    override fun countDownTime(partTime: Long): Observable<Int> {
        return timerDataSource.countTime(partTime)
            .map { it.toInt() } // long to int
            .map { partTime.toInt() + 1 - it } // reverse
    }
}