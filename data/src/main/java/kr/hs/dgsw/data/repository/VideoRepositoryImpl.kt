package kr.hs.dgsw.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.data.datasource.VideoDataSource
import kr.hs.dgsw.data.mapper.toDataEntity
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.repository.VideoRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
   private val videoDataSource: VideoDataSource
) : VideoRepository {
    override fun getVideoList(page: Int): Single<List<Video>> {
        return videoDataSource.getVideoList(page).map { videoList ->
            videoList.map { it.toEntity() }
        }.delay(500, TimeUnit.MILLISECONDS) // give users "HALF SECOND" to recognize that it is in loading
    }

    override fun getVideoDetail(url: String): Single<Video> {
        return videoDataSource.getVideoDetail(url)
            .map { it.toEntity() }
    }

    override fun insertVideo(video: Video): Completable {
        return videoDataSource.insertVideo(video.toDataEntity())
    }
}