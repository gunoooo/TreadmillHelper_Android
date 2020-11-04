package kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine

import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.treadmill_helper.R
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

    fun onClickMenu(view: View) {
        val menuButton = view as ImageView

        val popup = PopupMenu(view.context, menuButton)
        popup.inflate(R.menu.menu_routine_item)

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_detail -> {
                    getNavigator().onDetail(routine)
                    true
                }
                R.id.menu_modify -> {
                    getNavigator().onModify(routine)
                    true
                }
                R.id.menu_delete -> {
                    getNavigator().onDelete(routine)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }
}