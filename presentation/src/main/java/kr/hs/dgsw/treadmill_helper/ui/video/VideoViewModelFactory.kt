package kr.hs.dgsw.treadmill_helper.ui.video

import kr.hs.dgsw.domain.usecase.video.GetVideoListUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class VideoViewModelFactory @Inject constructor(
    getVideoListUseCase: GetVideoListUseCase
) : BaseViewModelFactory(
    getVideoListUseCase
)