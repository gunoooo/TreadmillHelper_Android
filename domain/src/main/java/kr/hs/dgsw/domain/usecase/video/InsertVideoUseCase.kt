package kr.hs.dgsw.domain.usecase.video

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.repository.VideoRepository
import javax.inject.Inject

class InsertVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : ParamsUseCase<InsertVideoUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        TODO("Not yet implemented")
    }

    data class Params(
        private val source: String
    )
}