package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.PartDataSource
import kr.hs.dgsw.data.datasource.RelatedVideoDataSource
import kr.hs.dgsw.data.datasource.RoutineDataSource
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.data.mapper.toRoutineDetailEntity
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.routine.Routine
import kr.hs.dgsw.domain.repository.RoutineRepository
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val routineDataSource: RoutineDataSource,
    private val partDataSource: PartDataSource,
    private val relatedVideoDataSource: RelatedVideoDataSource
) : RoutineRepository {
    override fun getRoutine(routineIdx: Int): Single<Routine> {
        return routineDataSource.getRoutine(routineIdx)
            .onErrorResumeNext {
                routineDataSource.insertRoutineList(Object.presetRoutineList)
                    .flatMap { routineList ->
                        partDataSource.insertPartList(routineList.flatMap { it.partList })
                            .andThen(relatedVideoDataSource.insertRelatedVideoList(routineList.flatMap { it.relatedVideoList })
                                .andThen(routineDataSource.getRoutine(routineIdx)))
                    }
            }
            .map { it.toEntity() }
    }

    override fun getRoutineList(): Single<List<Routine>> {
        return routineDataSource.getRoutineList()
            .onErrorResumeNext {
                routineDataSource.insertRoutineList(Object.presetRoutineList)
                        .flatMap { routineList ->
                            partDataSource.insertPartList(routineList.flatMap { it.partList })
                                .andThen(relatedVideoDataSource.insertRelatedVideoList(routineList.flatMap { it.relatedVideoList })
                                    .andThen(routineDataSource.getRoutineList()))
                        }
            }
            .map { routineList ->
                routineList.map {
                    it.toEntity()
                }
            }
    }

    override fun insertRoutine(routine: Routine): Completable {
        return routineDataSource.insertRoutine(routine.toDataEntity())
            .flatMapCompletable {
                partDataSource.insertPartList(it.partList)
                    .andThen(relatedVideoDataSource.insertRelatedVideoList(it.relatedVideoList))
            }
    }

    override fun updateRoutine(routine: Routine): Completable {
        return routineDataSource.deleteRoutine(routine.idx)
            .andThen(partDataSource.deletePartByRoutineIdx(routine.idx)
                .andThen(relatedVideoDataSource.deleteRelatedVideoByRoutineIdx(routine.idx)
                    .andThen(insertRoutine(routine))))
    }

    override fun getPartList(): Single<List<Part>> {
        return partDataSource.getPartList()
            .map { partList -> partList.map { it.toEntity() } }
    }

    override fun insertPart(routineIdx: Int, part: Part): Completable {
        return partDataSource.insertPart(part.toDataEntity(routineIdx))
    }

    override fun deletePart(partIdx: Int): Completable {
        return partDataSource.deletePart(partIdx)
    }

    override fun updatePart(routineIdx: Int, part: Part): Completable {
        return partDataSource.updatePart(part.toDataEntity(routineIdx))
    }

    override fun updatePartAll(routineIdx: Int, partList: List<Part>): Completable {
        return partDataSource.updatePartAll(routineIdx, partList.map { it.toDataEntity(routineIdx) })
    }

    override fun insertRelatedVideo(routineIdx: Int, relatedVideo: RelatedVideo): Completable {
        return relatedVideoDataSource.insertRelatedVideo(relatedVideo.toDataEntity(routineIdx))
    }

    override fun deleteRelatedVideo(relatedVideoIdx: Int): Completable {
        return relatedVideoDataSource.deleteRelatedVideo(relatedVideoIdx)
    }

    override fun updateRelatedVideoAll(
        routineIdx: Int,
        relatedVideoList: List<RelatedVideo>
    ): Completable {
        return relatedVideoDataSource.updateRelatedVideoAll(
            routineIdx,
            relatedVideoList.map { it.toDataEntity(routineIdx) }
        )
    }
}