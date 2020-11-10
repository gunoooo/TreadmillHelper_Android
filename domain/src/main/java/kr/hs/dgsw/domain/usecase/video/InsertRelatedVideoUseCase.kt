package kr.hs.dgsw.domain.usecase.video

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRelatedVideoUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : ParamsUseCase<InsertRelatedVideoUseCase.Params, Single<RelatedVideo>>() {
    override fun buildUseCaseObservable(params: Params): Single<RelatedVideo> {
        return routineRepository.insertRelatedVideo(
            routineIdx = params.routineIdx,
            relatedVideo = RelatedVideo(
                idx = 0,
                source = params.source,
                title = params.title,
                thumbnail = params.thumbnail,
                duration = params.duration,
                category = params.category
            )
        )
    }

    data class Params(
        val routineIdx: Int,
        val source: String,
        val title: String,
        val thumbnail: String,
        val duration: Int,
        val category: VideoCategoryEnum
    )
}