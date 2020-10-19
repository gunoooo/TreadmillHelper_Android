package kr.hs.dgsw.data.database

import android.content.Context
import android.content.SharedPreferences


object SharedPreferenceManager {
    private const val PREF_ROUTINE_IDX = "routine_idx"

    fun insertRoutineIdx(context: Context, routineIdx: Int) {
        getDefaultSharedPreferences(context).edit().putInt(PREF_ROUTINE_IDX, routineIdx).apply()
    }

    fun getRoutineIdx(context: Context): Int {
        return getDefaultSharedPreferences(context).getInt(PREF_ROUTINE_IDX, 1)
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}