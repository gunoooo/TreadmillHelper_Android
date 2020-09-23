package kr.hs.dgsw.treadmill_helper

import com.nhaarman.mockito_kotlin.mock
import kr.hs.dgsw.domain.usecase.ScheduleUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    lateinit var scheduleUseCase: ScheduleUseCase

    @Before
    fun setUp() {
        scheduleUseCase = mock()

        mainViewModel = MainViewModel(scheduleUseCase)
    }

    @Test
    fun schedule_requestIdx0() {
        mainViewModel.startTimer(0)
    }
}