package kr.hs.dgsw.domain.usecase.video

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoListUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : ParamsUseCase<GetVideoListUseCase.Params, Single<List<Video>>>() {
    override fun buildUseCaseObservable(params: Params): Single<List<Video>> {
        return videoRepository.getVideoList(params.page)
    }

    data class Params(
        val page: Int
    )
}