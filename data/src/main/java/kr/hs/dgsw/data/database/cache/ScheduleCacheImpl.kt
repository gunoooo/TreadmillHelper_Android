package kr.hs.dgsw.data.database.cache

import android.app.Application
import androidx.room.EmptyResultSetException
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleDetailEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import javax.inject.Inject

class ScheduleCacheImpl @Inject constructor(application: Application)
    : BaseCache(application), ScheduleCache {
    private val scheduleDao = database.scheduleDao()
    private val partDao = database.partDao()
    private val videoDao = database.videoDao()

    override fun getScheduleList(): Single<List<ScheduleEntity>> {
        return scheduleDao.getScheduleList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("schedule_table"))
            else
                Single.just(it)
        }
    }

    override fun getScheduleDetailList(): Single<List<ScheduleDetailEntity>> {
        return scheduleDao.getScheduleDetailList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("schedule_table"))
            else
                Single.just(it)
        }
    }

    override fun getScheduleDetail(scheduleIdx: Int): Single<ScheduleDetailEntity> {
        return scheduleDao.getScheduleDetail(scheduleIdx).onErrorResumeNext {
            if (it is EmptyResultSetException)
                Single.error(TableEmptyException(it))
            else
                Single.error(it)
        }
    }

    override fun insertScheduleDetailList(scheduleDetailEntityList: List<ScheduleDetailEntity>): Completable {
        return scheduleDao.insertGetIdx(scheduleDetailEntityList.map { it.schedule })
            .flatMapCompletable { idxList ->
                idxList.forEachIndexed { i, idx ->
                    scheduleDetailEntityList.flatMap { it.partList }
                        .forEach { it.scheduleIdx = idx.toInt() }
                    scheduleDetailEntityList.flatMap { it.relatedVideoList }
                        .forEach { it.scheduleIdx = idx.toInt() }
                }
                partDao.insert(scheduleDetailEntityList.flatMap { it.partList })
                    .andThen(videoDao.insert(scheduleDetailEntityList.flatMap { it.relatedVideoList }))
            }
    }

    override fun insertScheduleDetail(scheduleDetailEntity: ScheduleDetailEntity): Completable {
        return scheduleDao.insertGetIdx(scheduleDetailEntity.schedule)
            .flatMapCompletable { idx ->
                scheduleDetailEntity.partList
                    .forEach { it.scheduleIdx = idx.toInt() }
                scheduleDetailEntity.relatedVideoList
                    .forEach { it.scheduleIdx = idx.toInt() }
                partDao.insert(scheduleDetailEntity.partList)
                    .andThen(videoDao.insert(scheduleDetailEntity.relatedVideoList))
            }
    }

    override fun deleteSchedule(scheduleIdx: Int): Completable {
        return scheduleDao.deleteByIdx(scheduleIdx)
    }

    override fun updateSchedule(
        scheduleEntity: ScheduleEntity,
        partEntityList: List<PartEntity>,
        videoEntityList: List<VideoEntity>
    ): Completable {
        return videoDao.deleteByScheduleIdx(scheduleEntity.idx)
            .andThen(partDao.deleteByScheduleIdx(scheduleEntity.idx)
                .andThen(scheduleDao.deleteByIdx(scheduleEntity.idx)
                    .andThen(scheduleDao.insert(scheduleEntity)
                        .andThen(partDao.insert(partEntityList)
                            .andThen(videoDao.insert(videoEntityList))))))
    }
}