package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemVideoBinding

class VideoListAdapter(private val videoList: List<Video>) :
    RecyclerView.Adapter<VideoListAdapter.VideoItemViewHolder>(),
    VideoNavigator {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        return VideoItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: VideoItemViewHolder, position: Int) {
        viewHolder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class VideoItemViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = VideoItemViewModel()

        fun bind(model: Video) {
            viewModel.bind(model)
            viewModel.setNavigator(this@VideoListAdapter)
            binding.viewModel = viewModel
        }
    }
}