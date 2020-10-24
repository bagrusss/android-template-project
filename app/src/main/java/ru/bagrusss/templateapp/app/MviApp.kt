package ru.bagrusss.templateapp.app

import android.app.Application
import ru.bagrusss.templateapp.architecture.mvi.di.api.core.CoreApiProvider
import ru.bagrusss.templateapp.screens.mvi_example.di.DemoArguments
import ru.bagrusss.templateapp.screens.mvi_example.di.DemoModuleProvider

open class MviApp: Application() {

    override fun onCreate() {
        super.onCreate()

        CoreApiProvider.init {}

        DemoModuleProvider.init {
            DemoArguments(CoreApiProvider.instance)
        }
    }

}