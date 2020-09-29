package kr.hs.dgsw.treadmill_helper.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.item_timer.view.*
import kr.hs.dgsw.domain.entity.schedule.Part
import kr.hs.dgsw.domain.entity.schedule.Schedule
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.etc.extension.milliToMin
import kr.hs.dgsw.treadmill_helper.etc.extension.milliToSec
import kr.hs.dgsw.treadmill_helper.etc.extension.toMilliseconds

@SuppressLint("SetTextI18n")
class TimerView : FrameLayout {
    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.item_timer, null)

    lateinit var schedule: Schedule
    lateinit var part: Part
    private var partIndex: Int = 0

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        addView(view)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }

    fun initSchedule(schedule: Schedule) {
        this.schedule = schedule
        play()
        part_text_view.text = "${this.partIndex + 1} / ${this.schedule.partList.size}"
    }

    fun updateProgress(progress: Int) {
        timer_progress_bar.progress = progress - 1000
        minutes_text_view.text = progress.milliToMin()
        seconds_text_view.text = progress.milliToSec()
        val allRemainingTime = getAllRemainingTime(progress)
        all_minutes_text_view.text = allRemainingTime.milliToMin()
        all_seconds_text_view.text = allRemainingTime.milliToSec()
    }

    fun updatePart(part: Part, partIndex: Int) {
        this.part = part
        this.partIndex = partIndex
        val drawable = ((ContextCompat.getDrawable(mContext!!, R.drawable.progress) as LayerDrawable)
            .findDrawableByLayerId(R.id.progress) as GradientDrawable)
        drawable.setColor(Color.parseColor(this.part.color.toRGB()))
        timer_progress_bar.progressDrawable = drawable
        timer_progress_bar.max = this.part.time.toMilliseconds()
        part_text_view.text = "${this.partIndex + 1} / ${this.schedule.partList.size}"
    }

    fun play() = this.state_image_view.setImageResource(R.drawable.ic_pause)

    fun pause() = this.state_image_view.setImageResource(R.drawable.ic_play)

    private fun getAllRemainingTime(currentRemainingTime: Int): Int {
        return currentRemainingTime +
                schedule.partList.mapIndexed { index: Int, part: Part ->
                    if (index > partIndex)
                        part.time
                    else
                        0
                }.sum().toMilliseconds()
    }
}