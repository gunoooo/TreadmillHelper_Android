package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.datasource.RoutineDataSource
import kr.hs.dgsw.data.datasource.RoutineDataSourceImpl
import kr.hs.dgsw.data.datasource.VideoDataSource
import kr.hs.dgsw.data.datasource.VideoDataSourceImpl
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Singleton
    @Provides
    fun provideRoutineDataSource(routineDataSourceImpl: RoutineDataSourceImpl): RoutineDataSource = routineDataSourceImpl

    @Singleton
    @Provides
    fun provideVideoDataSource(videoDataSourceImpl: VideoDataSourceImpl): VideoDataSource = videoDataSourceImpl
}