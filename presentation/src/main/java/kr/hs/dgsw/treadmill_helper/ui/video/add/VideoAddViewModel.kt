package kr.hs.dgsw.treadmill_helper.ui.video.add

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.video.GetVideoDetailUseCase
import kr.hs.dgsw.domain.usecase.video.InsertVideoUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class VideoAddViewModel(
    private val getVideoDetailUseCase: GetVideoDetailUseCase,
    private val insertVideoUseCase: InsertVideoUseCase
) : BaseViewModel() {
    private var video: Video? = null

    val isLoaded = MutableLiveData<Boolean>()
    val thumbnailData = MutableLiveData<String>()
    val titleData = MutableLiveData<String>()
    val durationData = MutableLiveData<String>()

    fun clearData() {
        isLoading.value = false
        isLoaded.value = false
        video = null
        thumbnailData.value = null
        titleData.value = null
        durationData.value = null
    }

    fun setVideo(url: String) {
        isLoading.value = true
        isLoaded.value = false
        addDisposable(
            getVideoDetailUseCase.buildUseCaseObservable(
                GetVideoDetailUseCase.Params(url)
            ),
            object : DisposableSingleObserver<Video>() {
                override fun onSuccess(t: Video) {
                    isLoading.value = false
                    isLoaded.value = true
                    video = t
                    thumbnailData.value = t.thumbnail
                    titleData.value = t.title
                    durationData.value = t.duration.secToTimeFormat()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                }
            }
        )
    }
}