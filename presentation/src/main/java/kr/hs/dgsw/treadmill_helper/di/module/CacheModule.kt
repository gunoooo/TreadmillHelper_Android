package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.database.cache.RoutineCache
import kr.hs.dgsw.data.database.cache.RoutineCacheImpl
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun provideRoutineCache(routineCacheImpl: RoutineCacheImpl): RoutineCache = routineCacheImpl
}