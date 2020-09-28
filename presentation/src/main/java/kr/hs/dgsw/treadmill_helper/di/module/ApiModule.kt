package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.network.api.ScheduleApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideScheduleApi(retrofit: Retrofit): ScheduleApi =
        retrofit.create(ScheduleApi::class.java)
}