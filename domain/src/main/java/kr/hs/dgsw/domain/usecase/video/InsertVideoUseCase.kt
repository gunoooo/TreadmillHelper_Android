package kr.hs.dgsw.domain.usecase.video

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.repository.VideoRepository
import javax.inject.Inject

class InsertVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : ParamsUseCase<InsertVideoUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        return videoRepository.insertVideo(
            Video(
                title = params.title,
                thumbnail = params.thumbnail,
                source = params.source,
                duration = params.duration,
                category = params.category
            )
        )
    }

    data class Params(
        val title: String,
        val thumbnail: String,
        val source: String,
        val duration: Int,
        val category: VideoCategoryEnum
    )
}