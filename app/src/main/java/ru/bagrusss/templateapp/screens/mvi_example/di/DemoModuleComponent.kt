package ru.bagrusss.templateapp.screens.mvi_example.di

import dagger.Component
import ru.bagrusss.architecture.mvi.common.LazySingletonHolder
import ru.bagrusss.architecture.mvi.common.ModuleInitArgs
import ru.bagrusss.architecture.mvi.di.ModuleScope
import ru.bagrusss.architecture.mvi.di.api.core.CoreApi
import ru.bagrusss.templateapp.screens.mvi_example.api.DemoModuleApi
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.di.DemoFragmentInjector

@ModuleScope
@Component(
    modules = [DemoModule::class],
    dependencies = [
        CoreApi::class
    ]
)
interface DemoModuleComponent : DemoModuleApi,

    DemoFragmentInjector {

    @Component.Factory
    interface Factory {
        fun create(
            coreApi: CoreApi
        ): DemoModuleComponent
    }

}

data class DemoArguments(
    internal val coreApi: CoreApi
) : ModuleInitArgs

internal object DemoModuleProvider : LazySingletonHolder<DemoModuleComponent, DemoArguments>({ args ->
    DaggerDemoModuleComponent.factory()
        .create(args.coreApi)

}) {

    internal val component: DemoModuleComponent
        get() = instance

}