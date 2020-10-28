package kr.hs.dgsw.treadmill_helper.ui.video

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_video.*
import kr.hs.dgsw.domain.entity.e.VideoCategoryEnum
import kr.hs.dgsw.treadmill_helper.base.BaseFragment
import kr.hs.dgsw.treadmill_helper.databinding.FragmentVideoBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.ui.video.add.VideoAddDialog
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
        initUI()
        initOnClickEvent()
    }

    private fun initUI() {
        initRecyclerView()
        initSpinner()
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

    private fun initSpinner() {
        val adapter =
            ArrayAdapter<String>(requireContext(),
                R.layout.simple_spinner_item,
                VideoCategoryEnum.values().map { it.name })
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    }

    private fun initOnClickEvent() {
        video_add_fab.setOnClickListener {
            val videoAddDialog = VideoAddDialog()
            videoAddDialog.show(parentFragmentManager)
            videoAddDialog.addVideoEvent.observe(viewLifecycleOwner, Observer {
                mViewModel.apply {
                    page = 0
                    isLastPage = false
                    videoList.clear()
                    videoListAdapter.notifyDataSetChanged()
                    setVideoList()
                }
            })
        }
    }
}