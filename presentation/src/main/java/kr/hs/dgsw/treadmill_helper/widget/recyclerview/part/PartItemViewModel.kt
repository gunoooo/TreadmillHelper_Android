package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel

class PartItemViewModel : BaseItemViewModel<PartNavigator>() {
    private lateinit var part: Part

    val color = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val incline = MutableLiveData<String>()
    val speed = MutableLiveData<String>()

    fun bind(model: Part) {
        this.part = model

        this.color.value = this.part.color.toRGB()
        this.title.value = this.part.title
        this.incline.value = "incline : ${this.part.incline}"
        this.speed.value = "speed : ${this.part.speed}km/h"
    }

    fun onClickPlus30sec() {
        getNavigator().plus30sec()
    }

    fun onClickMinus30sec() {
        getNavigator().minus30sec()
    }
}