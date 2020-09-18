package kr.hs.dgsw.domain.usecase.schedule

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.model.schedule.Part
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetPartListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ParamsUseCase<GetPartListUseCase.Params, Single<List<Part>>>() {
    override fun buildUseCaseObservable(params: GetPartListUseCase.Params): Single<List<Part>> {
        return scheduleRepository.getPartList(params.scheduleIdx)
    }

    data class Params(
        val scheduleIdx: Int
    )
}