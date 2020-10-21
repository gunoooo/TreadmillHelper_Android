package kr.hs.dgsw.treadmill_helper.ui.video

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_video.*
import kr.hs.dgsw.treadmill_helper.base.BaseFragment
import kr.hs.dgsw.treadmill_helper.databinding.FragmentVideoBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class VideoFragment : BaseFragment<FragmentVideoBinding, VideoViewModel>() {
    @Inject
    lateinit var viewModelFactory: VideoViewModelFactory

    override val viewModel: VideoViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        video_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0)
                    video_add_fab.hide()
                else
                    video_add_fab.show()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCREEN_STATE_OFF) {
                    if (!mViewModel.isLastPage) {
                        if (!mViewModel.videoList.contains(null)) {
                            mViewModel.page++
                            mViewModel.setVideoList()
                        }
                    }
                }
            }
        })
    }
}