package kr.hs.dgsw.treadmill_helper.di.module

import kr.hs.dgsw.treadmill_helper.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.treadmill_helper.ui.main.MainActivity
import kr.hs.dgsw.treadmill_helper.ui.routine.detail.RoutineDetailActivity
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingTimerActivity(): TimerActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingRoutineDetailActivity(): RoutineDetailActivity
}