package com.ayoolamasha.starwars.entryPoint

import android.app.Application
import android.content.Context
import com.ayoolamasha.starwars.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application(){
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
            context = applicationContext

        }

    }

}