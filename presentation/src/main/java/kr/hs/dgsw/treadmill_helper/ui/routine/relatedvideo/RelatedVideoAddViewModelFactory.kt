package kr.hs.dgsw.treadmill_helper.ui.routine.relatedvideo

import kr.hs.dgsw.domain.usecase.video.GetVideoListUseCase
import kr.hs.dgsw.domain.usecase.video.InsertRelatedVideoUseCase
import kr.hs.dgsw.treadmill_helper.base.BaseViewModelFactory
import javax.inject.Inject

open class RelatedVideoAddViewModelFactory @Inject constructor(
    insertRelatedVideoUseCase: InsertRelatedVideoUseCase,
    getVideoListUseCase: GetVideoListUseCase
) : BaseViewModelFactory(
    insertRelatedVideoUseCase,
    getVideoListUseCase
)