package kr.hs.dgsw.treadmill_helper.ui.video.add

import kr.hs.dgsw.domain.usecase.video.GetVideoDetailUseCase
import kr.hs.dgsw.domain.usecase.video.InsertVideoUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class VideoAddViewModelFactory @Inject constructor(
    getVideoDetailUseCase: GetVideoDetailUseCase,
    insertVideoUseCase: InsertVideoUseCase
) : BaseViewModelFactory(
    getVideoDetailUseCase,
    insertVideoUseCase
)