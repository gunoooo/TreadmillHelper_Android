package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.workout.Part
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class PartItemViewModel : BaseItemViewModel<PartNavigator>() {
    private lateinit var part: Part

    val colorData = MutableLiveData<String>()
    val titleData = MutableLiveData<String>()
    val inclineData = MutableLiveData<String>()
    val speedData = MutableLiveData<String>()
    val durationData = MutableLiveData<String>()
    val calorieData = MutableLiveData<String>()

    fun bind(model: Part) {
        part = model

        colorData.value = part.color.toRGB()
        titleData.value = part.title
        inclineData.value = "incline : ${part.incline}"
        speedData.value = "speed : ${part.speed}km/h"
        durationData.value = "duration : ${part.time.secToTimeFormat()}"
        calorieData.value = "${part.getCalorie()}kcal"
    }

    fun onClickPlus30sec() {
        getNavigator().plus30sec()
    }

    fun onClickMinus30sec() {
        getNavigator().minus30sec()
    }
}