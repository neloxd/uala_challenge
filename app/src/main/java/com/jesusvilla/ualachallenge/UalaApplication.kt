package com.jesusvilla.ualachallenge

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Jesus Villa on 7/12/24.
 */

@HiltAndroidApp
class UalaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //For dynamic theming on Android 12 and above
        DynamicColors.applyToActivitiesIfAvailable(this)
        Timber.plant(Timber.DebugTree())
    }
}