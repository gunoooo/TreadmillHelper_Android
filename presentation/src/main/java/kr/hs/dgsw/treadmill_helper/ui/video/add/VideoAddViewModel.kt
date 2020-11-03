package kr.hs.dgsw.treadmill_helper.ui.video.add

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.video.GetVideoDetailUseCase
import kr.hs.dgsw.domain.usecase.video.InsertVideoUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class VideoAddViewModel(
    private val getVideoDetailUseCase: GetVideoDetailUseCase,
    private val insertVideoUseCase: InsertVideoUseCase
) : BaseViewModel() {
    private var video: Video? = null

    val isLoaded = MutableLiveData<Boolean>(false)
    val thumbnailData = MutableLiveData<String>()
    val titleData = MutableLiveData<String>()
    val durationData = MutableLiveData<String>()

    val addVideoEvent = SingleLiveEvent<Unit>()

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
                    isLoading.value = false
                    errorEvent.value = e
                }
            }
        )
    }

    private fun insertVideo() {
        isLoading.value = true
        isLoaded.value = false
        addDisposable(
            insertVideoUseCase.buildUseCaseObservable(
                InsertVideoUseCase.Params(
                    title = video!!.title,
                    thumbnail = video!!.thumbnail,
                    source = video!!.source,
                    duration = video!!.duration,
                    category = video!!.category
                )
            ),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    isLoading.value = false
                    addVideoEvent.call()
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    errorEvent.value = e
                }
            }
        )
    }

    fun onClickApply() {
        val isVideoEmpty = video == null
        if (isLoaded.value!! && !isVideoEmpty)
            insertVideo()
    }
}