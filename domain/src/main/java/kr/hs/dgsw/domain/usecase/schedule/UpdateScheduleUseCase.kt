package kr.hs.dgsw.domain.usecase.schedule

import io.reactivex.Completable
import kr.hs.dgsw.domain.base.ParamsUseCase
import kr.hs.dgsw.domain.entity.Schedule
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class UpdateScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ParamsUseCase<UpdateScheduleUseCase.Params, Completable>() {
    override fun buildUseCaseObservable(params: UpdateScheduleUseCase.Params): Completable {
        return scheduleRepository.updateSchedule(params.schedule)
    }

    data class Params(
        val schedule: Schedule
    )
}