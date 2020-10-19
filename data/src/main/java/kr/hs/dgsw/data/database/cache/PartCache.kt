package kr.hs.dgsw.data.database.cache

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.entity.PartEntity

interface PartCache {
    fun getPartList(): Single<List<PartEntity>>

    fun insertPart(routineIdx: Int, partEntity: PartEntity): Completable

    fun deletePart(partIdx: Int): Completable

    fun updatePart(partIdx: Int, partEntity: PartEntity): Completable
}