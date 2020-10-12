package kr.hs.dgsw.treadmill_helper.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.base.BaseActivity
import kr.hs.dgsw.treadmill_helper.databinding.ActivityMainBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel
        get() = getViewModel()

    override fun observerViewModel() {

    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.page_fragment)
        bottom_navigation_view.setupWithNavController(navController)
    }
}