package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.secToTimeFormat

class VideoModifyItemViewModel : BaseItemViewModel<VideoModifyNavigator>() {
    private lateinit var relatedVideo: RelatedVideo

    val thumbnailData = MutableLiveData<String>()
    val titleData = MutableLiveData<String>()
    val durationData = MutableLiveData<String>()
    val categoryData = MutableLiveData<String>()

    fun bind(model: RelatedVideo) {
        relatedVideo = model

        thumbnailData.value = relatedVideo.thumbnail
        titleData.value = relatedVideo.title
        durationData.value = relatedVideo.duration.secToTimeFormat()
        categoryData.value = relatedVideo.category.toString()
    }
}