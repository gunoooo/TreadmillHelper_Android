package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.repository.RoutineRepositoryImpl
import kr.hs.dgsw.data.repository.TimerRepositoryImpl
import kr.hs.dgsw.domain.repository.RoutineRepository
import kr.hs.dgsw.domain.repository.TimerRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideTimerRepository(): TimerRepository = TimerRepositoryImpl()

    @Singleton
    @Provides
    fun provideRoutineRepository(routineRepositoryImpl: RoutineRepositoryImpl): RoutineRepository = routineRepositoryImpl
}