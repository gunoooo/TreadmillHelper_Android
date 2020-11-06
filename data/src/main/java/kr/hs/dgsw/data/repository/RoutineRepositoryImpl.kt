package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.PartDataSource
import kr.hs.dgsw.data.datasource.RelatedVideoDataSource
import kr.hs.dgsw.data.datasource.RoutineDataSource
import kr.hs.dgsw.data.entity.RoutineData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toEntity
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
                getPresetRoutineList()
                    .map { routineList ->
                        routineList.find {
                            it.idx == routineIdx
                        }
                    }
            }
            .map { it.toEntity() }
    }

    override fun getRoutineList(): Single<List<Routine>> {
        return routineDataSource.getRoutineList()
            .onErrorResumeNext {
                getPresetRoutineList()
            }
            .map { routineList ->
                routineList.map {
                    it.toEntity()
                }
            }
    }

    private fun getPresetRoutineList(): Single<List<RoutineData>> {
        return routineDataSource.insertRoutineList(Object.presetRoutineList)
            .flatMap { routineList ->
                partDataSource.insertPartList(routineList.flatMap { it.partList })
                    .flatMap { partList ->
                        relatedVideoDataSource.insertRelatedVideoList(routineList.flatMap { it.relatedVideoList })
                            .flatMap { relatedVideoList ->
                                routineList.forEach { routineData ->
                                    routineData.partList =
                                        partList.filter {
                                            it.routineIdx == routineData.idx
                                        }
                                    routineData.relatedVideoList =
                                        relatedVideoList.filter {
                                            it.routineIdx == routineData.idx
                                        }
                                }
                                Single.just(routineList)
                            }
                    }
            }
    }

    override fun insertRoutine(routine: Routine): Single<Routine> {
        return routineDataSource.insertRoutine(routine.toDataEntity())
            .flatMap { routineData ->
                partDataSource.insertPartList(routineData.partList)
                    .flatMap { partList ->
                        routineData.partList = partList
                        relatedVideoDataSource.insertRelatedVideoList(routineData.relatedVideoList)
                            .flatMap { relatedVideoList ->
                                routineData.relatedVideoList = relatedVideoList
                                Single.just(routineData)
                            }
                    }
            }
            .map { it.toEntity() }
    }

    override fun updateRoutine(routine: Routine): Single<Routine> {
        return routineDataSource.deleteRoutine(routine.idx)
            .andThen(partDataSource.deletePartByRoutineIdx(routine.idx)
                .andThen(relatedVideoDataSource.deleteRelatedVideoByRoutineIdx(routine.idx)
                    .andThen(insertRoutine(routine))))
    }

    override fun getPartList(): Single<List<Part>> {
        return partDataSource.getPartList()
            .map { partList -> partList.map { it.toEntity() } }
    }

    override fun getPresetPartList(): Single<List<Part>> {
        return Single.just(Object.presetPartList.map { it.toEntity() })
    }

    override fun insertPart(routineIdx: Int, part: Part): Single<Part> {
        return partDataSource.insertPart(part.toDataEntity(routineIdx))
            .map { it.toEntity() }
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

    override fun insertRelatedVideo(routineIdx: Int, relatedVideo: RelatedVideo): Single<RelatedVideo> {
        return relatedVideoDataSource.insertRelatedVideo(relatedVideo.toDataEntity(routineIdx))
            .map { it.toEntity() }
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