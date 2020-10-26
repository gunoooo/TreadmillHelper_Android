package kr.hs.dgsw.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.database.cache.VideoCache
import kr.hs.dgsw.data.database.entity.RelatedVideoEntity
import kr.hs.dgsw.data.database.entity.VideoEntity
import kr.hs.dgsw.data.entity.VideoData
import kr.hs.dgsw.data.etc.Object
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toDbEntity
import kr.hs.dgsw.data.mapper.toRoutineDetailEntity
import kr.hs.dgsw.data.network.remote.VideoRemote
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(
    private val remote: VideoRemote,
    private val cache: VideoCache
) : VideoDataSource {
    override fun getVideoList(page: Int): Single<List<VideoData>> {
        return if (page == 0) {
            cache.getVideoList()
                .map { videoList ->
                    videoList.map {
                        it.toDataEntity()
                    }
                }
        } else {
            remote.getVideoList(
                page,
                Object.VIDEO_ITEM_SIZE
            )
        }
    }

    override fun getVideoDetail(url: String): Single<VideoData> {
        return remote.getVideoDetail(url)
    }

    override fun insertRelatedVideo(
        routineIdx: Int,
        relatedVideoEntity: RelatedVideoEntity
    ): Completable {
        TODO("Not yet implemented")
    }
}