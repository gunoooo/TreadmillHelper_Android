package kr.hs.dgsw.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.base.BaseCache
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.exception.TableEmptyException
import java.lang.Exception
import javax.inject.Inject

class VideoCacheImpl @Inject constructor(application: Application) :
    BaseCache(application), VideoCache {
    private val videoDao = database.videoDao()
    private val relatedVideo = database.relatedVideoDao()

    override fun getVideoList(): Single<List<VideoEntity>> {
        return videoDao.getVideoList().flatMap {
            if (it.isEmpty())
                Single.error(TableEmptyException("video_table"))
            else
                Single.just(it)
        }
    }

    override fun insertVideo(videoEntity: VideoEntity): Completable {
        return videoDao.insert(videoEntity)
    }

    override fun insertVideoList(videoEntityList: List<VideoEntity>): Completable {
        return videoDao.insert(videoEntityList)
    }

    override fun insertRelatedVideo(relatedVideoEntity: RelatedVideoEntity): Completable {
        return relatedVideo.insert(relatedVideoEntity)
    }

    override fun deleteRelatedVideo(relatedVideoIdx: Int): Completable {
        return relatedVideo.deleteByIdx(relatedVideoIdx)
    }
}