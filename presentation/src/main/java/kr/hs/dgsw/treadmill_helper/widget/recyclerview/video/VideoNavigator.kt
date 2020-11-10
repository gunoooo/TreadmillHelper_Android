package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video

import kr.hs.dgsw.domain.entity.video.Video

interface VideoNavigator {
    fun onClickItem(video: Video)
}