package kr.hs.dgsw.domain.usecase.schedule

import io.reactivex.Single
import kr.hs.dgsw.domain.base.BaseUseCase
import kr.hs.dgsw.domain.model.schedule.Schedule
import kr.hs.dgsw.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetScheduleListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : BaseUseCase<Single<List<Schedule>>>() {
    override fun buildUseCaseObservable(): Single<List<Schedule>> {
        return scheduleRepository.getScheduleList()
    }
}