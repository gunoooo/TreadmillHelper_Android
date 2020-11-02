package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.datasource.*
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Singleton
    @Provides
    fun provideRoutineDataSource(routineDataSourceImpl: RoutineDataSourceImpl): RoutineDataSource = routineDataSourceImpl

    @Singleton
    @Provides
    fun providePartDataSource(partDataSourceImpl: PartDataSourceImpl): PartDataSource = partDataSourceImpl

    @Singleton
    @Provides
    fun provideRelatedVideoDataSource(relatedVideoDataSourceImpl: RelatedVideoDataSourceImpl): RelatedVideoDataSource = relatedVideoDataSourceImpl

    @Singleton
    @Provides
    fun provideVideoDataSource(videoDataSourceImpl: VideoDataSourceImpl): VideoDataSource = videoDataSourceImpl
}