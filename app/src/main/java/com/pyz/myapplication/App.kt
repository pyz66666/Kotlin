package com.pyz.myapplication

import android.app.Application

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

    companion object{
        private lateinit var myApplication:Application
        fun getApplication() = myApplication
    }


    override fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks?) {
        super.registerActivityLifecycleCallbacks(callback)
    }
}