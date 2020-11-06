package kr.hs.dgsw.treadmill_helper.ui.routine.part.add

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.dialog_part_add.*
import kr.hs.dgsw.domain.entity.routine.Part
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.base.dialog.BaseDialog
import kr.hs.dgsw.treadmill_helper.databinding.DialogPartAddBinding
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.*
import kr.hs.dgsw.treadmill_helper.widget.TimerPickerDialog
import javax.inject.Inject


class PartAddDialog(
    private val routineIdx: Int
) : BaseDialog<DialogPartAddBinding, PartAddViewModel>() {
    @Inject
    lateinit var viewModelFactory: PartAddViewModelFactory

    override val viewModel: PartAddViewModel
        get() = getViewModel(viewModelFactory)

    val successEvent = SingleLiveEvent<Part>()

    override fun observerViewModel() {
        with(mViewModel) {
            successEvent.observe(this@PartAddDialog, Observer {
                this@PartAddDialog.successEvent.value = it
                dismiss()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickEvent()
    }

    private fun initOnClickEvent() {
        drop_down_btn.setOnClickListener {
            dropDownTitleEditText()
        }

        time_text_view.setOnClickListener {
            openTimePickerDialog()
        }

        color_image_view.setOnClickListener {
            openColorPickerDialog()
        }

        apply_btn.setOnClickListener {
            mViewModel.insertPart(routineIdx)
        }
    }

    private fun dropDownTitleEditText() {
        val popUp =
            getPopupWindow(mViewModel.presetPartList.map { it.title })
        popUp.showAsDropDown(title_input, 0, 0)
        drop_down_btn.animate()
            .rotationX((drop_down_btn.rotationX + 180) % 360)
            .setDuration(500)
            .start()
    }

    private fun getPopupWindow(titleList: List<String>): PopupWindow {
        val popupWindow = PopupWindow(context)
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            context!!, android.R.layout.simple_dropdown_item_1line,
            titleList
        )

        val titleListView = ListView(context)
        titleListView.adapter = adapter
        titleListView.setOnItemClickListener { parent, view, position, id ->
            mViewModel.setPart(position)
            popupWindow.dismiss()
        }

        popupWindow.isFocusable = true
        popupWindow.width = title_input.width
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT

        popupWindow.contentView = titleListView
        popupWindow.setOnDismissListener {
            drop_down_btn.animate()
                .rotationX((drop_down_btn.rotationX + 180) % 360)
                .setDuration(500)
                .start()
        }
        return popupWindow
    }

    private fun openTimePickerDialog() {
        val timerPickerDialog = TimerPickerDialog(
            context!!,
            mViewModel.part.time.secToHour(),
            mViewModel.part.time.secToMin(),
            mViewModel.part.time.secToSec()
        )
        timerPickerDialog.updateTimeEvent.observe(viewLifecycleOwner, Observer {
            mViewModel.part.time = it
            mViewModel.timeData.value = it.secToTimeFormat()
        })
        timerPickerDialog.show()
    }

    private fun openColorPickerDialog() {
        ColorPickerDialogBuilder
            .with(context, R.style.ColorPickerTheme)
            .initialColor(Color.parseColor(mViewModel.colorData.value))
            .setTitle("CHOOSE COLOR")
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setPositiveButton("APPLY") { dialog, selectedColor, allColors ->
                mViewModel.part.color = selectedColor.toArgbString()
                mViewModel.colorData.value = selectedColor.toArgbString()
            }
            .setNegativeButton("CANCEL") { dialog, which ->

            }
            .build()
            .show()
    }
}