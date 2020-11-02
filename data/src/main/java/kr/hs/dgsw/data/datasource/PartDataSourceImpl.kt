package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.PartCache
import kr.hs.dgsw.data.entity.PartData
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toDbEntity
import javax.inject.Inject

class PartDataSourceImpl @Inject constructor(
    private val cache: PartCache
) : PartDataSource {
    override fun getPartList(): Single<List<PartData>> {
        return cache.getPartList()
            .map { partList -> partList.map { it.toDataEntity() } }
    }

    override fun insertPart(partData: PartData): Completable {
        return cache.insertPart(partData.toDbEntity())
    }

    override fun insertPartList(partDataList: List<PartData>): Completable {
        return cache.insertPartList(partDataList.map { it.toDbEntity() })
    }

    override fun deletePart(partIdx: Int): Completable {
        return cache.deletePart(partIdx)
    }

    override fun deletePartByRoutineIdx(routineIdx: Int): Completable {
        return cache.deletePartByRoutineIdx(routineIdx)
    }

    override fun updatePart(partData: PartData): Completable {
        return cache.updatePart(partData.toDbEntity())
    }

    override fun updatePartAll(routineIdx: Int, partDataList: List<PartData>): Completable {
        return cache.updatePartAll(routineIdx, partDataList.map { it.toDbEntity() })
    }
}