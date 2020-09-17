package kr.hs.dgsw.data.datasource

import android.os.CountDownTimer
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.callback.TimerCallback

class TimerDataSource {

    fun countTime(partList: List<Part>, timerCallback: TimerCallback) {
        countTime(partList, 0, timerCallback)
    }

    private fun countTime(partList: List<Part>, partIdx: Int, timerCallback: TimerCallback) {
        object : CountDownTimer((partList[partIdx].time * 1000).toLong(), 1000) {
            override fun onFinish() {
                if (partIdx == partList.size - 1) {
                    timerCallback.call()
                    return
                }
                timerCallback.onPart(partList[partIdx + 1])
                countTime(partList, partIdx + 1, timerCallback)
            }

            override fun onTick(millisUntilFinished: Long) {
                timerCallback.onRun(millisUntilFinished.toInt())
            }
        }.start()
    }
}