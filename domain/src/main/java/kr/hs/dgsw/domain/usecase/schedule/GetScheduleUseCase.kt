package kr.hs.dgsw.domain.usecase.schedule

import io.reactivex.Single
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.schedule.Schedule
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ParamsUseCase<GetScheduleUseCase.Params, Single<Schedule>>() {
    override fun buildUseCaseObservable(params: Params): Single<Schedule> {
        return scheduleRepository.getSchedule(params.scheduleIdx)
    }

    data class Params(
        val scheduleIdx: Int
    )
}