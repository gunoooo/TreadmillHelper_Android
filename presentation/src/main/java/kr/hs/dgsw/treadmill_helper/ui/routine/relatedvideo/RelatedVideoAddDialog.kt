package kr.hs.dgsw.treadmill_helper.ui.routine.relatedvideo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import kr.hs.dgsw.domain.entity.routine.RelatedVideo
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogRelatedVideoAddBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.getViewModel
import javax.inject.Inject

class RelatedVideoAddDialog(
    private val routineIdx: Int
) : BaseDialog<DialogRelatedVideoAddBinding, RelatedVideoAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: RelatedVideoAddViewModelFactory

    override val viewModel: RelatedVideoAddViewModel
        get() = getViewModel(viewModelFactory)

    val successEvent = SingleLiveEvent<RelatedVideo>()

    override fun observerViewModel() {
        with(mViewModel) {
            with(videoListAdapter) {
                clickItemEvent.observe(this@RelatedVideoAddDialog, Observer {
                    insertRelatedVideo(routineIdx, it)
                })
            }

            successEvent.observe(this@RelatedVideoAddDialog, Observer {
                this@RelatedVideoAddDialog.successEvent.value = it
                dismiss()
            })
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(RelativeLayout(activity))
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            resources.displayMetrics.widthPixels * 5 / 6,
            resources.displayMetrics.heightPixels * 5 / 6)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }
}