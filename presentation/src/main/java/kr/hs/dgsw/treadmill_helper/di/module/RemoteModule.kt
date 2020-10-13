package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.network.remote.RoutineRemote
import kr.hs.dgsw.data.network.remote.RoutineRemoteImpl
import javax.inject.Singleton

@Module
class RemoteModule {
    @Singleton
    @Provides
    fun provideRoutineRemote(routineRemoteImpl: RoutineRemoteImpl): RoutineRemote = routineRemoteImpl
}