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
import kotlinx.android.synthetic.main.layout_timer.view.*
import kr.hs.dgsw.domain.entity.workout.Part
import kr.hs.dgsw.domain.entity.workout.Routine
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.etc.extension.milliToTimeFormat
import kr.hs.dgsw.treadmill_helper.etc.extension.toMilliseconds

@SuppressLint("SetTextI18n")
class TimerView : FrameLayout {
    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.layout_timer, null)

    lateinit var routine: Routine
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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        part_text_view.textSize = w / 40F
        time_text_view.textSize = w / 15F
        all_time_text_view.textSize = w / 40F
    }

    fun initRoutine(routine: Routine) {
        this.routine = routine
        play()
        part_text_view.text = "${this.partIndex + 1} / ${this.routine.partList.size}"
    }

    fun updateProgress(progress: Int) {
        if (progress - 1000 > timer_progress_bar.max)
            timer_progress_bar.max  = progress - 1000
        timer_progress_bar.progress = progress - 1000
        time_text_view.text = progress.milliToTimeFormat()
        all_time_text_view.text = getAllRemainingTime(progress).milliToTimeFormat()
    }

    fun updatePart(part: Part, partIndex: Int) {
        this.part = part
        this.partIndex = partIndex
        val drawable = ((ContextCompat.getDrawable(mContext!!, R.drawable.background_progress) as LayerDrawable)
            .findDrawableByLayerId(R.id.progress) as GradientDrawable)
        drawable.setColor(Color.parseColor(this.part.color.toRGB()))
        timer_progress_bar.progressDrawable = drawable
        timer_progress_bar.max = this.part.time.toMilliseconds()
        if (::routine.isInitialized)
            part_text_view.text = "${this.partIndex + 1} / ${this.routine.partList.size}"
    }

    fun play() = this.state_image_view.setImageDrawable(null)

    fun pause() = this.state_image_view.setImageResource(R.drawable.ic_play)

    private fun getAllRemainingTime(currentRemainingTime: Int): Int {
        if (::routine.isInitialized)
            return currentRemainingTime +
                routine.partList.mapIndexed { index: Int, part: Part ->
                    if (index > partIndex)
                        part.time
                    else
                        0
                }.sum().toMilliseconds()
        else
            return 0
    }
}