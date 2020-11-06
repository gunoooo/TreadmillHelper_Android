package kr.hs.dgsw.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.domain.entity.routine.Routine

interface RoutineRepository {
    // Routine
    fun getRoutine(routineIdx: Int): Single<Routine>

    fun getRoutineList(): Single<List<Routine>>

    fun insertRoutine(routine: Routine): Single<Routine>

    fun updateRoutine(routine: Routine): Single<Routine>

    // Part
    fun getPartList(): Single<List<Part>>

    fun getPresetPartList(): Single<List<Part>>

    fun insertPart(routineIdx: Int, part: Part): Single<Part>

    fun deletePart(partIdx: Int): Completable

    fun updatePart(routineIdx: Int, part: Part): Completable

    fun updatePartAll(routineIdx: Int, partList: List<Part>): Completable

    // RelatedVideo
    fun insertRelatedVideo(routineIdx: Int, relatedVideo: RelatedVideo): Single<RelatedVideo>

    fun deleteRelatedVideo(relatedVideoIdx: Int): Completable

    fun updateRelatedVideoAll(routineIdx: Int, relatedVideoList: List<RelatedVideo>): Completable
}