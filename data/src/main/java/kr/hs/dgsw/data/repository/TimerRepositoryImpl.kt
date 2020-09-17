package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.data.datasource.ScheduleDataSource
import kr.hs.dgsw.data.datasource.TimerDataSource
import kr.hs.dgsw.domain.callback.TimerCallback
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val timerDataSource: TimerDataSource,
    private val scheduleDataSource: ScheduleDataSource
) : TimerRepository {

    override fun schedule(scheduleIdx: Int, timerCallback: TimerCallback): Completable {
        return scheduleDataSource.getPartList(scheduleIdx).flatMapCompletable {
            timerDataSource.countTime(it, timerCallback)
            Completable.fromCallable(timerCallback)
        }
    }
}