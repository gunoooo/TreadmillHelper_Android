package kr.hs.dgsw.treadmill_helper.ui.routine.add

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.usecase.routine.InsertRoutineUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent

class RoutineAddViewModel(
    private val insertRoutineUseCase: InsertRoutineUseCase
) : BaseViewModel() {
    var titleData = MutableLiveData<String>()

    val successEvent = SingleLiveEvent<Routine>()

    private fun insertRoutine() {
        isLoading.value = true
        addDisposable(
            insertRoutineUseCase.buildUseCaseObservable(
                InsertRoutineUseCase.Params(
                    title = titleData.value!!
                )
            ),
            object : DisposableSingleObserver<Routine>() {
                override fun onSuccess(t: Routine) {
                    isLoading.value = false
                    successEvent.value = t
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    errorEvent.value = e
                }
            }
        )
    }

    fun onClickApply() {
        val isTitleEmpty = titleData.value == null || titleData.value!!.trim().isEmpty()
        if (!isTitleEmpty)
            insertRoutine()
    }
}