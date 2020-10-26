package kr.hs.dgsw.domain.usecase.video

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoDetailUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : ParamsUseCase<GetVideoDetailUseCase.Params, Single<Video>>() {
    override fun buildUseCaseObservable(params: Params): Single<Video> {
        return videoRepository.getVideoDetail(params.url)
    }

    data class Params(
        val url: String
    )
}