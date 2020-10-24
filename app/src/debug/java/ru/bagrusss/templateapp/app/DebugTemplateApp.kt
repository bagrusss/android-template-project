package ru.bagrusss.templateapp.app

import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * Created by bagrusss on 19.01.2020
 */
class DebugTemplateApp : MviApp() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
    }

}