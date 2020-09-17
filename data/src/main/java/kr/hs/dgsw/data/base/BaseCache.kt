package kr.hs.dgsw.data.base

import android.app.Application
import kr.hs.dgsw.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}