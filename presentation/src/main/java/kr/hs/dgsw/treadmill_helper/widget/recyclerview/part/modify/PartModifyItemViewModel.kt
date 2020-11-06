package kr.hs.dgsw.treadmill_helper.widget.recyclerview.part.modify

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class PartModifyItemViewModel : BaseItemViewModel<PartModifyNavigator>() {
    private lateinit var part: Part

    val titleData = MutableLiveData<String>()
    val colorData = MutableLiveData<String>()
    val speedData = MutableLiveData<String>()
    val timeData = MutableLiveData<String>()
    val inclineData = MutableLiveData<String>()
    val calorieData = MutableLiveData<String>()

    fun bind(model: Part) {
        part = model

        titleData.value = part.title
        colorData.value = part.color
        speedData.value = "speed : ${part.speed}km/h"
        timeData.value = "time : ${part.time.secToTimeFormat()}"
        inclineData.value = "incline : ${part.incline}"
        calorieData.value = "calorie : ${part.getCalorie()}kcal"
    }
}