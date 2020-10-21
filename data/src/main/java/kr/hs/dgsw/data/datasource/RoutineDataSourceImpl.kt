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
    override fun getRoutineDetailList(): Single<List<RoutineData>> {
        return cache.getRoutineDetailList()
            .onErrorResumeNext {
                cache.insertRoutineDetailList(Object.presetRoutineList
                    .map { it.toRoutineDetailEntity() })
                    .andThen(Single.defer { cache.getRoutineDetailList() })
            }
            .map { routineList ->
                routineList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun getRoutineList(): Single<List<RoutineData>> {
        return cache.getRoutineList()
            .onErrorResumeNext {
                cache.insertRoutineDetailList(Object.presetRoutineList
                    .map { it.toRoutineDetailEntity() })
                    .andThen(Single.defer { cache.getRoutineList() })
            }
            .map { routineList ->
                routineList.map {
                    it.toDataEntity()
                }
            }
    }

    override fun getRoutine(routineIdx: Int): Single<RoutineData> {
        return cache.getRoutineDetail(routineIdx)
            .onErrorResumeNext {
                cache.insertRoutineDetailList(Object.presetRoutineList
                    .map { it.toRoutineDetailEntity() })
                    .andThen(Single.defer { cache.getRoutineDetail(routineIdx) })
            }
            .map { it.toDataEntity() }
    }

    override fun insertRoutine(routineData: RoutineData): Completable {
        return cache.insertRoutineDetail(routineData.toRoutineDetailEntity())
    }

    override fun updateRoutine(routineData: RoutineData): Completable {
        return cache.updateRoutine(
            routineData.toDbEntity(routineData.idx),
            routineData.partList.map { it.toDbEntity(it.idx, routineData.idx) },
            routineData.relatedVideoList.map { it.toDbEntity(it.idx, routineData.idx) }
        )
    }
}