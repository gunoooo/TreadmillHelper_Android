package kr.hs.dgsw.treadmill_helper.di.module

import com.gunwoo.karaoke.singsangsung.di.scope.PerActivity
import com.gunwoo.karaoke.singsangsung.di.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.treadmill_helper.ui.home.HomeFragment
import kr.hs.dgsw.treadmill_helper.ui.main.MainActivity
import kr.hs.dgsw.treadmill_helper.ui.menu.MenuFragment
import kr.hs.dgsw.treadmill_helper.ui.record.RecordFragment
import kr.hs.dgsw.treadmill_helper.ui.timer.TimerActivity
import kr.hs.dgsw.treadmill_helper.ui.video.VideoFragment

@Module
abstract class FragmentBindingModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingVideoFragment(): VideoFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingRecordFragment(): RecordFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMenuFragment(): MenuFragment
}