package kr.hs.dgsw.treadmill_helper.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.data.network.remote.RoutineRemote
import kr.hs.dgsw.data.network.remote.RoutineRemoteImpl
import kr.hs.dgsw.data.network.remote.VideoRemote
import kr.hs.dgsw.data.network.remote.VideoRemoteImpl
import javax.inject.Singleton

@Module
class RemoteModule {
    @Singleton
    @Provides
    fun provideRoutineRemote(routineRemoteImpl: RoutineRemoteImpl): RoutineRemote = routineRemoteImpl

    @Singleton
    @Provides
    fun provideVideoRemote(videoRemoteImpl: VideoRemoteImpl): VideoRemote = videoRemoteImpl
}