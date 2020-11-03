package kr.hs.dgsw.treadmill_helper.ui.routine.list

import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.usecase.routine.GetRoutineListUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.routine.RoutineListAdapter

class RoutineListViewModel(
    private val getRoutineListUseCase: GetRoutineListUseCase
) : BaseViewModel() {
    private val routineList = ArrayList<Routine>()

    val routineListAdapter = RoutineListAdapter(routineList)

    init {
        setRoutineList()
    }

    private fun setRoutineList() {
        addDisposable(getRoutineListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Routine>>() {
                override fun onSuccess(t: List<Routine>) {
                    routineList.refreshAll(t)
                    routineListAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e
                }
            })
    }
}