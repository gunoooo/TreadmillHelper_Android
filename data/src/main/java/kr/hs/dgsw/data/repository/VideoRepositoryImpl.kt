package kr.hs.dgsw.data.repository

import io.reactivex.Single
import kr.hs.dgsw.data.datasource.VideoDataSource
import kr.hs.dgsw.data.mapper.toEntity
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
   private val videoDataSource: VideoDataSource
) : VideoRepository {
    override fun getVideoList(page: Int): Single<List<Video>> {
        return videoDataSource.getVideoList(page).map { videoList ->
            videoList.map { it.toEntity() }
        }
    }
}