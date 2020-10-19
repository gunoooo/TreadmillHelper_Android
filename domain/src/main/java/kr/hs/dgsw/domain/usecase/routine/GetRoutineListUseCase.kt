package kr.hs.dgsw.domain.usecase.routine

import io.reactivex.Single
import kr.hs.dgsw.domain.base.BaseUseCase
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class GetRoutineListUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : BaseUseCase<Single<List<Routine>>>() {
    override fun buildUseCaseObservable(): Single<List<Routine>> {
        return routineRepository.getRoutineList()
    }
}