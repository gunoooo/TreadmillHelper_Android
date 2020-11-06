package kr.hs.dgsw.treadmill_helper.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.dialog_time_picker.*
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.etc.SingleLiveEvent
import kr.hs.dgsw.treadmill_helper.etc.extension.format

class TimerPickerDialog(
    context: Context,
    private var hours: Int,
    private var minutes: Int,
    private var seconds: Int
) : Dialog(context) {
    val updateTimeEvent = SingleLiveEvent<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_time_picker)
        initUI()
        initNumberPicker()
        initOnClickEvent()
        refreshTimeText()
    }

    private fun initUI() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            context.resources.displayMetrics.widthPixels * 5 / 6,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initNumberPicker() {
        initNumberPicker(
            hour_number_picker,
            (0..23).map { it.format(2) }.toTypedArray(),
            hours
        )
        initNumberPicker(
            minute_number_picker,
            (0..59).map { it.format(2) }.toTypedArray(),
            minutes
        )
        initNumberPicker(
            second_number_picker,
            (0..59).filter { it % 10 == 0 }.map { it.format(2) }.toTypedArray(),
            seconds / 10
        )
        initNumberPickerOnValueChangedEvent()
    }

    private fun initNumberPicker(numberPicker: NumberPicker, data: Array<String>, value: Int) {
        numberPicker.minValue = 1
        numberPicker.maxValue = data.size
        numberPicker.displayedValues = data
        numberPicker.value = value + 1
        numberPicker.wrapSelectorWheel = true
    }

    private fun initNumberPickerOnValueChangedEvent() {
        hour_number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            hours = newVal - 1
            refreshTimeText()
        }
        minute_number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            minutes = newVal - 1
            refreshTimeText()
        }
        second_number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            seconds = (newVal - 1) * 10
            refreshTimeText()
        }
    }

    private fun initOnClickEvent() {
        apply_btn.setOnClickListener {
            updateTimeEvent.value =
                (hours * 60 * 60) + (minutes * 60) + seconds
            dismiss()
        }
        cancel_btn.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun refreshTimeText() {
        time_text_view.text =
            "${hours.format(2)} : ${minutes.format(2)} : ${seconds.format(2)}"
    }
}