package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.datasource.ScheduleDataSource
import kr.hs.dgsw.data.datasource.ScheduleDataSourceImpl
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Singleton
    @Provides
    fun provideScheduleDataSource(scheduleDataSourceImpl: ScheduleDataSourceImpl): ScheduleDataSource = scheduleDataSourceImpl
}