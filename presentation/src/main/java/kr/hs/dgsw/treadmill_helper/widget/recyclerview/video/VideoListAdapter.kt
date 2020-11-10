package kr.hs.dgsw.treadmill_helper.widget.recyclerview.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.domain.entity.video.Video
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.databinding.ItemLoadingBinding
import kr.hs.dgsw.treadmill_helper.databinding.ItemVideoBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.widget.recyclerview.LoadingViewType

class VideoListAdapter(private val videoList: List<Video?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    VideoNavigator {
    val clickItemEvent = SingleLiveEvent<Video>()
    override fun onClickItem(video: Video) {
        clickItemEvent.value = video
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LoadingViewType.ITEM.value ->
                VideoItemViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_video,
                        parent, false
                    )
                )
            else ->
                LoadingItemViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_loading,
                        parent, false
                    )
                )
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val video = videoList[position]
        if (viewHolder is VideoItemViewHolder)
            viewHolder.bind(video!!)
    }

    override fun getItemViewType(position: Int): Int {
        return if (videoList[position] == null)
            LoadingViewType.LOADING.value
        else
            LoadingViewType.ITEM.value
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

    inner class LoadingItemViewHolder(val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)
}