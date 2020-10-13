package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.RoutineDataSource
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.domain.entity.workout.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val routineDataSource: RoutineDataSource
) : RoutineRepository {
    override fun getRoutine(routineIdx: Int): Single<Routine> {
        return routineDataSource.getRoutine(routineIdx)
            .map { it.toEntity() }
    }

    override fun getRoutineList(): Single<List<Routine>> {
        return routineDataSource.getRoutineList()
            .map { routineDataList -> routineDataList.map { it.toEntity() } }
    }

    override fun insertRoutine(routine: Routine): Completable {
        return routineDataSource.insertRoutine(routine.toDataEntity())
    }

    override fun updateRoutine(routine: Routine): Completable {
        return routineDataSource.updateRoutine(routine.toDataEntity())
    }
}