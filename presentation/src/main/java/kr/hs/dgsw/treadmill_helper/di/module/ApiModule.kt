package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.network.api.RoutineApi
import kr.hs.dgsw.data.network.api.VideoApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideRoutineApi(retrofit: Retrofit): RoutineApi =
        retrofit.create(RoutineApi::class.java)

    @Singleton
    @Provides
    fun provideVideoApi(retrofit: Retrofit): VideoApi =
        retrofit.create(VideoApi::class.java)
}