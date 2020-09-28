package kr.hs.dgsw.treadmill_helper.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kr.hs.dgsw.treadmill_helper.TreadmillHelperApplication
import kr.hs.dgsw.treadmill_helper.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetWorkModule::class,
        ApiModule::class,
        CacheModule::class,
        RemoteModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ActivityBindingModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<TreadmillHelperApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}