package kr.hs.dgsw.data.repository

import kr.hs.dgsw.domain.repository.TimerRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimerRepositoryTest {
    private lateinit var timerRepository: TimerRepository

    @Before
    fun setUp() {
        timerRepository = TimerRepositoryImpl()
    }

    @Test
    fun countDownTime_success() {
        val partTime = 5
        val testObserver = timerRepository.countDownTime(partTime).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertValueAt(0, 5)
            .assertValueAt(1, 4)
            .assertValueAt(2, 3)
            .assertValueAt(3, 2)
            .assertValueAt(4, 1)
            .assertComplete()
    }
}