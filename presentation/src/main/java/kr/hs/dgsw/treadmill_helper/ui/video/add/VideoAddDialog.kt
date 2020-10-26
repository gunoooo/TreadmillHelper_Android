package kr.hs.dgsw.treadmill_helper.ui.video.add

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.dialog_video_add.*
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogVideoAddBinding
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import kr.hs.dgsw.treadmill_helper.etc.extension.isYouTubeUrl
import javax.inject.Inject

class VideoAddDialog : BaseDialog<DialogVideoAddBinding, VideoAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: VideoAddViewModelFactory

    override val viewModel: VideoAddViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideoEditText()
    }

    private fun initVideoEditText() {
        video_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                val url = s.toString()
                if (url.isYouTubeUrl())
                    mViewModel.setVideo(url)
            }
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        mViewModel.clearData()
        super.onDismiss(dialog)
    }
}