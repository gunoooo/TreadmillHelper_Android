package kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class RoutineItemViewModel : BaseItemViewModel<RoutineNavigator>() {
    private lateinit var routine: Routine

    val titleData = MutableLiveData<String>()
    val calorieData = MutableLiveData<String>()
    val distanceData = MutableLiveData<String>()
    val timeData = MutableLiveData<String>()

    fun bind(model: Routine) {
        routine = model

        titleData.value = routine.title
        calorieData.value = "${routine.getCalorie()}kcal"
        distanceData.value = "distance : ${routine.getDistance()}km"
        timeData.value = "time : ${routine.getTime().secToTimeFormat()}"
    }

    fun onClickItem() {
        getNavigator().onClickItem(routine)
    }
}