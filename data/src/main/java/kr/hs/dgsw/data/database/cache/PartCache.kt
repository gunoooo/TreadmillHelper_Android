package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity

interface PartCache {
    fun getPartList(): Single<List<PartEntity>>

    fun insertPart(partEntity: PartEntity): Single<PartEntity>

    fun insertPartList(partEntityList: List<PartEntity>): Single<List<PartEntity>>

    fun deletePart(partIdx: Int): Completable

    fun deletePartByRoutineIdx(routineIdx: Int): Completable

    fun updatePart(partEntity: PartEntity): Completable

    fun updatePartAll(routineIdx: Int, partEntityList: List<PartEntity>): Completable
}