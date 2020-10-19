package kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine

import kr.hs.dgsw.domain.entity.routine.Routine

interface RoutineNavigator {
    fun onClickItem(routine: Routine)
}