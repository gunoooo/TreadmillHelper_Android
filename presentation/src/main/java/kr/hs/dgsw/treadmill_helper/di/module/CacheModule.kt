package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.database.cache.ScheduleCache
import kr.hs.dgsw.data.database.cache.ScheduleCacheImpl
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun provideScheduleCache(scheduleCacheImpl: ScheduleCacheImpl): ScheduleCache = scheduleCacheImpl
}