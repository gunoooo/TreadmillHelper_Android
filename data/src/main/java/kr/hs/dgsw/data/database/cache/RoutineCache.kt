package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity

interface RoutineCache {
    fun getRoutineList(): Single<List<RoutineDetailEntity>>

    fun getRoutine(routineIdx: Int): Single<RoutineDetailEntity>

    fun insertRoutineList(routineDetailEntityList: List<RoutineDetailEntity>): Single<List<RoutineDetailEntity>>

    fun insertRoutine(routineDetailEntity: RoutineDetailEntity): Single<RoutineDetailEntity>

    fun deleteRoutine(routineIdx: Int): Completable
}