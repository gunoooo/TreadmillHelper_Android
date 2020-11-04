package kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine

import kr.hs.dgsw.domain.entity.routine.Routine

interface RoutineNavigator {
    fun onClickItem(routine: Routine)
    fun onDetail(routine: Routine)
    fun onModify(routine: Routine)
    fun onDelete(routine: Routine)
}