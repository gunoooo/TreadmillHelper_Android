package kr.hs.dgsw.treadmill_helper.di.module

import com.gunwoo.karaoke.singsangsung.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.treadmill_helper.ui.main.MainActivity

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity
}