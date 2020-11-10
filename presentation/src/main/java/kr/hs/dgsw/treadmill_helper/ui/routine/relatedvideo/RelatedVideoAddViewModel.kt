package kr.hs.dgsw.treadmill_helper.ui.routine.relatedvideo

import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.usecase.video.GetVideoListUseCase
import kr.hs.dgsw.domain.usecase.video.InsertRelatedVideoUseCase
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseViewModel
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.VideoListAdapter

class RelatedVideoAddViewModel(
    private val insertRelatedVideoUseCase: InsertRelatedVideoUseCase,
    private val getVideoListUseCase: GetVideoListUseCase
) : BaseViewModel() {
    var page = 0
    var isLastPage = false
    val videoList = ArrayList<Video?>()

    val videoListAdapter = VideoListAdapter(videoList)

    val successEvent = SingleLiveEvent<RelatedVideo>()

    init {
        setVideoList()
    }

    fun setVideoList() {
        startLoading()
        addDisposable(getVideoListUseCase.buildUseCaseObservable(GetVideoListUseCase.Params(page)),
            object : DisposableSingleObserver<List<Video>>() {
                override fun onSuccess(t: List<Video>) {
                    stopLoading()

                    val beforeListSize = videoList.size
                    videoList.addAll(t)
                    if (beforeListSize == 0)
                        videoListAdapter.notifyDataSetChanged()
                    else
                        videoListAdapter.notifyItemRangeInserted(beforeListSize, videoList.size - beforeListSize)

                    if (page == 0) {
                        page++
                        setVideoList()
                        return
                    }

                    isLastPage = t.size < Object.VIDEO_ITEM_SIZE
                }

                override fun onError(e: Throwable) {
                    stopLoading()
                    errorEvent.value = e
                }
            })
    }

    fun insertRelatedVideo(routineIdx: Int, video: Video) {
        addDisposable(
            insertRelatedVideoUseCase.buildUseCaseObservable(
                InsertRelatedVideoUseCase.Params(
                    routineIdx = routineIdx,
                    source = video.source,
                    title = video.title,
                    thumbnail = video.thumbnail,
                    duration = video.duration,
                    category = video.category
                )
            ),
            object : DisposableSingleObserver<RelatedVideo>() {
                override fun onSuccess(t: RelatedVideo) {
                    successEvent.value = t
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e
                }
            }
        )
    }

    private fun startLoading() {
        videoList.add(null)
        videoListAdapter.notifyItemInserted(videoList.size - 1)
    }

    private fun stopLoading() {
        videoList.remove(null)
        videoListAdapter.notifyItemRemoved(videoList.size)
    }
}