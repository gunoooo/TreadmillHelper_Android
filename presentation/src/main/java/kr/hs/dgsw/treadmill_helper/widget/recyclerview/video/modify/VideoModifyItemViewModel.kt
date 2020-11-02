package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video.modify

import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.treadmill_helper.base.viewmodel.BaseItemViewModel

class VideoModifyItemViewModel : BaseItemViewModel<VideoModifyNavigator>() {
    private lateinit var relatedVideo: RelatedVideo
}