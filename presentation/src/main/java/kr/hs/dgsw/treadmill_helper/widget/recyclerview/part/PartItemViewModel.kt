package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel

class PartItemViewModel : BaseItemViewModel<PartNavigator>() {
    private lateinit var part: Part

    val colorData = MutableLiveData<String>()
    val titleData = MutableLiveData<String>()
    val inclineData = MutableLiveData<String>()
    val speedData = MutableLiveData<String>()

    fun bind(model: Part) {
        part = model

        colorData.value = part.color.toRGB()
        titleData.value = part.title
        inclineData.value = "incline : ${part.incline}"
        speedData.value = "speed : ${part.speed}km/h"
    }

    fun onClickPlus30sec() {
        getNavigator().plus30sec()
    }

    fun onClickMinus30sec() {
        getNavigator().minus30sec()
    }
}