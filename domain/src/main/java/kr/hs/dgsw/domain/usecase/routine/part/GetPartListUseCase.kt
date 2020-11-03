package kr.hs.dgsw.domain.usecase.routine.part

import io.reactivex.Single
import kr.hs.dgsw.domain.base.BaseUseCase
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class GetPartListUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : BaseUseCase<Single<List<Part>>>() {
    override fun buildUseCaseObservable(): Single<List<Part>> {
        return routineRepository.getPartList()
    }
}