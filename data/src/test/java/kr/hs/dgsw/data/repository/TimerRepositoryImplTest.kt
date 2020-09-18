package kr.hs.dgsw.data.repository

import com.nhaarman.mockito_kotlin.mock
import kr.hs.dgsw.data.datasource.TimerDataSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimerRepositoryImplTest {
    private lateinit var timerRepositoryImpl: TimerRepositoryImpl

    private lateinit var timerDataSource: TimerDataSource

    @Before
    fun setUp() {
        timerDataSource = mock()
        timerRepositoryImpl = TimerRepositoryImpl(
            timerDataSource
        )
    }

    @Test
    fun countTime_When_TimePart_0() {
        val testObserver = timerRepositoryImpl.countDownTime(0).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoValues()
            .assertComplete()
    }

    @Test
    fun countTime_When_TimePart_3() {
        val testObserver = timerRepositoryImpl.countDownTime(3).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertValueAt(0, 3)
            .assertValueCount(3)
            .assertComplete()
    }

    @Test
    fun countTime_When_TimePart_30() {
        val testObserver = timerRepositoryImpl.countDownTime(30).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertValueAt(0, 30)
            .assertValueCount(30)
            .assertComplete()
    }
}