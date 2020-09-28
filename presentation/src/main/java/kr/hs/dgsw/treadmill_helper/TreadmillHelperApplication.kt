package kr.hs.dgsw.treadmill_helper

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kr.hs.dgsw.treadmill_helper.di.component.DaggerAppComponent
import javax.inject.Inject

class TreadmillHelperApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()
}