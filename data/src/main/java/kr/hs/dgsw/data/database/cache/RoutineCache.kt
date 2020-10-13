package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.RoutineEntity
import kr.hs.dgsw.data.database.entity.RoutineDetailEntity
import kr.hs.dgsw.data.database.entity.VideoEntity

interface RoutineCache {
    fun getRoutineList(): Single<List<RoutineEntity>>

    fun getRoutineDetailList(): Single<List<RoutineDetailEntity>>

    fun getRoutineDetail(routineIdx: Int): Single<RoutineDetailEntity>

    fun insertRoutineDetailList(routineDetailEntityList: List<RoutineDetailEntity>): Completable

    fun insertRoutineDetail(routineDetailEntity: RoutineDetailEntity): Completable

    fun deleteRoutine(routineIdx: Int): Completable

    fun updateRoutine(
        routineEntity: RoutineEntity,
        partEntityList: List<PartEntity> = emptyList(),
        videoEntityList: List<VideoEntity> = emptyList()
    ): Completable
}