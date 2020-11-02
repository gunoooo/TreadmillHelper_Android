package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.database.cache.*
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun provideRoutineCache(routineCacheImpl: RoutineCacheImpl): RoutineCache = routineCacheImpl

    @Singleton
    @Provides
    fun providePartCache(partCacheImpl: PartCacheImpl): PartCache = partCacheImpl

    @Singleton
    @Provides
    fun provideRelatedVideoCache(relatedVideoCacheImpl: RelatedVideoCacheImpl): RelatedVideoCache = relatedVideoCacheImpl

    @Singleton
    @Provides
    fun provideVideoCache(videoCacheImpl: VideoCacheImpl): VideoCache = videoCacheImpl
}