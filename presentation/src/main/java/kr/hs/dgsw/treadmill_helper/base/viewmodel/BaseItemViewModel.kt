package kr.hs.dgsw.treadmill_helper.base.viewmodel

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseItemViewModel<N> : ViewModel() {

    private lateinit var navigator: WeakReference<N>

    fun getNavigator(): N {
        return navigator.get()!!
    }
    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    override fun onCleared() {
        super.onCleared()
        navigator.clear()
    }
}