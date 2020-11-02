package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.RoutineCache
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toDbEntity
import kr.hs.dgsw.data.mapper.toRoutineDetailEntity
import javax.inject.Inject

class RoutineDataSourceImpl @Inject constructor(
    private val cache: RoutineCache
) : RoutineDataSource {
    override fun getRoutineList(): Single<List<RoutineData>> {
        return cache.getRoutineList()
            .map { routineList ->
                routineList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun getRoutine(routineIdx: Int): Single<RoutineData> {
        return cache.getRoutine(routineIdx)
            .map { it.toDataEntity() }
    }

    override fun insertRoutineList(routineDataList: List<RoutineData>): Single<List<RoutineData>> {
        return cache.insertRoutineList(routineDataList.map { it.toRoutineDetailEntity() })
            .map { routineList ->
                routineList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun insertRoutine(routineData: RoutineData): Single<RoutineData> {
        return cache.insertRoutine(routineData.toRoutineDetailEntity())
            .map { it.toDataEntity() }
    }

    override fun deleteRoutine(routineIdx: Int): Completable {
        return cache.deleteRoutine(routineIdx)
    }
}