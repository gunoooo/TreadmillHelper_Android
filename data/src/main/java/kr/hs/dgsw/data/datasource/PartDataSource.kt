package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.entity.PartData

interface PartDataSource {
    fun getPartList(): Single<List<PartData>>

    fun insertPart(partData: PartData): Single<PartData>

    fun insertPartList(partDataList: List<PartData>): Single<List<PartData>>

    fun deletePart(partIdx: Int): Completable

    fun deletePartByRoutineIdx(routineIdx: Int): Completable

    fun updatePart(partData: PartData): Completable

    fun updatePartAll(routineIdx: Int, partDataList: List<PartData>): Completable
}