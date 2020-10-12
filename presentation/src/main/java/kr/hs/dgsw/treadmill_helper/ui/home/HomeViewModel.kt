package kr.hs.dgsw.treadmill_helper.ui.home

import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent

class HomeViewModel : BaseViewModel() {
    val openTimerActivityEvent = SingleLiveEvent<Unit>()

    fun onClickStart() {
        openTimerActivityEvent.call()
    }
}