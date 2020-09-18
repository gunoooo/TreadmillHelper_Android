package kr.hs.dgsw.data.datasource

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimerDataSourceTest {
    private lateinit var timerDataSource: TimerDataSource

    @Before
    fun setUp() {
        timerDataSource = TimerDataSource()
    }

    @Test
    fun countTime_When_TimePart_0() {
        val testObserver = timerDataSource.countTime(0).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoValues()
            .assertComplete()
    }

    @Test
    fun countTime_When_TimePart_3() {
        val testObserver = timerDataSource.countTime(3).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertValueAt(0, 1)
            .assertValueCount(3)
            .assertComplete()
    }

    @Test
    fun countTime_When_TimePart_30() {
        val testObserver = timerDataSource.countTime(30).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertValueAt(0, 1)
            .assertValueCount(30)
            .assertComplete()
    }
}