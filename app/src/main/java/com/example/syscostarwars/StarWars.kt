package com.example.syscostarwars

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarWars : Application(){
    companion object {
        private var instance: StarWars? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }

    init {
        instance = this
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

    override fun onCreate() {
        super.onCreate()

    }

}