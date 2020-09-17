package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.PartEntity
import kr.hs.dgsw.data.database.entity.ScheduleEntity
import kr.hs.dgsw.data.database.entity.ScheduleWithPartEntity
import javax.inject.Inject

class ScheduleCache @Inject constructor(application: Application) : BaseCache(application) {
    private val scheduleDao = database.scheduleDao()
    private val partDao = database.partDao()

    fun getScheduleList(): Single<List<ScheduleEntity>> {
        return scheduleDao.getScheduleList()
    }

    fun getScheduleWithPartList(): Single<List<ScheduleWithPartEntity>> {
        return scheduleDao.getScheduleWithPartList()
    }

    fun getPartList(scheduleIdx: Int): Single<List<PartEntity>> {
        return partDao.getPartList(scheduleIdx)
    }
}