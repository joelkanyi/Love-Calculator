package com.kanyideveloper.lovecalculator

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class LoveCalculatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}