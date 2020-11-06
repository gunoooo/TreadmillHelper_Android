package kr.hs.dgsw.treadmill_helper.ui.routine.part.add

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.extension.refreshAll
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.usecase.routine.part.GetPresetPartListUseCase
import kr.hs.dgsw.domain.usecase.routine.part.InsertPartUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class PartAddViewModel(
    private val getPresetPartListUseCase: GetPresetPartListUseCase,
    private val insertPartUseCase: InsertPartUseCase
) : BaseViewModel() {
    lateinit var part: Part
    val presetPartList = ArrayList<Part>()

    val titleData = MutableLiveData<String>()
    val timeData = MutableLiveData<String>()
    val colorData =  MutableLiveData<String>()
    val speedData = MutableLiveData<String>()
    val inclineData = MutableLiveData<String>()

    val successEvent = SingleLiveEvent<Part>()

    init {
        setPresetPartList()
    }

    private fun setPresetPartList() {
        addDisposable(getPresetPartListUseCase.buildUseCaseObservable(),
            object : DisposableSingleObserver<List<Part>>() {
                override fun onSuccess(t: List<Part>) {
                    presetPartList.refreshAll(t)
                    setPart(0)
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e
                }
            })
    }

    fun setPart(idx: Int) {
        part = presetPartList[idx]
        titleData.value = part.title
        timeData.value = part.time.secToTimeFormat()
        colorData.value = part.color
        speedData.value = part.speed.toString()
        inclineData.value = part.incline.toString()
    }

    fun insertPart(routineIdx: Int) {
        addDisposable(insertPartUseCase.buildUseCaseObservable(
            InsertPartUseCase.Params(
                routineIdx = routineIdx,
                title = titleData.value!!,
                time = part.time,
                color = colorData.value!!,
                speed = speedData.value!!.toDouble(),
                incline = inclineData.value!!.toInt()
            )
        ), object : DisposableSingleObserver<Part>() {
            override fun onSuccess(t: Part) {
                successEvent.value = t
            }

            override fun onError(e: Throwable) {
                errorEvent.value = e
            }
        })
    }
}