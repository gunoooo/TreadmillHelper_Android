package kr.hs.dgsw.domain.callback

import kr.hs.dgsw.domain.model.schedule.Part
import java.util.concurrent.Callable

/**
 * At the end of all, it calls call()
 */
interface TimerCallback : Callable<Unit> {
    fun onRun(second: Int) // At every second
    fun onPart(part: Part) // At the end of each part, param is next part
}