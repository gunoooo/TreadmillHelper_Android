package kr.hs.dgsw.data

import android.util.Log
import kr.hs.dgsw.data.datasource.ScheduleDataSource
import kr.hs.dgsw.data.datasource.TimerDataSource
import kr.hs.dgsw.data.repository.TimerRepositoryImpl
import kr.hs.dgsw.domain.callback.TimerCallback
import kr.hs.dgsw.domain.model.schedule.Part
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class TimerRepositoryImplTest {

    private val TAG = "TimerRepositoryImplTest"

    private lateinit var timerRepositoryImpl: TimerRepositoryImpl

    private lateinit var timerDataSource: TimerDataSource
    private lateinit var scheduleDataSource: ScheduleDataSource

    @Before
    fun setUp() {
        timerDataSource = mock(TimerDataSource::class.java)
        scheduleDataSource = mock(ScheduleDataSource::class.java)
        timerRepositoryImpl = TimerRepositoryImpl(
            timerDataSource,
            scheduleDataSource
        )
    }

    @Test
    fun scheduleWhenScheduleIdx0() {
        timerRepositoryImpl.schedule(0, object : TimerCallback {
            override fun onRun(second: Int) {
                Log.d(TAG, second.toString())
            }

            override fun onPart(part: Part) {
                Log.d(TAG, part.time.toString())
            }

            override fun call() {
                Log.d(TAG, "")
            }
        }).test().assertComplete()
    }
}