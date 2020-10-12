package kr.hs.dgsw.treadmill_helper.etc.extension

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

fun Fragment.shortToast(message: String?) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.shortToast(message: Int) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String?) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Fragment.longToast(message: Int) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Fragment.startActivity(activity: Class<*>) {
    startActivity(Intent(context!!.applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun Fragment.startActivityWithFinish(activity: Class<*>) {
    startActivityWithFinish(Intent(context!!.applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun Fragment.startActivityWithFinish(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    this.activity!!.finish()
}

fun Fragment.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(context!!.applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.activity!!.finishAffinity()
}

fun Fragment.startActivitiesWithFinish(vararg activity: Class<*>) {
    val intents: ArrayList<Intent> = ArrayList()
    for (clazz in activity) {
        val intent = Intent(context!!.applicationContext, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intents.add(intent)
    }
    this.activity!!.startActivities(intents.toArray(arrayOf<Intent?>()))
    this.activity!!.finish()
}

inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, factory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.getViewModel(): T =
    ViewModelProvider(this)[T::class.java]