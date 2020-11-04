package kr.hs.dgsw.treadmill_helper.etc

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.treadmill_helper.R
import kr.hs.dgsw.treadmill_helper.etc.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("touchHelper")
fun setTouchHelper(view: RecyclerView, touchHelper: ItemTouchHelper) {
    touchHelper.attachToRecyclerView(view)
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
}

@BindingAdapter("mutableTextColor")
fun setMutableTextColor(view: TextView, color: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    color.observe(parentActivity, Observer { value ->
        view.setTextColor(Color.parseColor(value))
    })
}

@BindingAdapter("mutableColor")
fun setMutableColor(view: View, color: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    color.observe(parentActivity, Observer { value ->
        view.setBackgroundColor(Color.parseColor(value))
    })
}

@BindingAdapter("mutableTint")
fun setMutableTint(view: ImageView, tint: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    tint.observe(parentActivity, Observer { value ->
        view.setColorFilter(Color.parseColor(value))
    })
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    url.observe(parentActivity, Observer { value ->
        Glide.with(view.context)
            .load(value)
            .error(R.color.colorBackground)
            .into(view)
    })
}