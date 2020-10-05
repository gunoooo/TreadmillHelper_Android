package kr.hs.dgsw.treadmill_helper.etc

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import kr.hs.dgsw.treadmill_helper.etc.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    text.observe(parentActivity, Observer { value -> view.text = value?:""})
}

@BindingAdapter("mutableColor")
fun setMutableColor(view: View, color: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return
    color.observe(parentActivity, Observer { value ->
        view.setBackgroundColor(Color.parseColor(value))
    })
}