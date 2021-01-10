package ru.bagrusss.arch.rx_mvi.di.api.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.bagrusss.arch.rx_mvi.common.LazySingletonHolder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreApiModule::class
    ]
)
internal interface CoreComponent : CoreApi {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): CoreApi
    }

}

data class CoreArgs(
    @JvmField val context: Context
)

object CoreApiProvider : LazySingletonHolder<CoreApi, CoreArgs>({ args ->
    DaggerCoreComponent.factory()
        .create(args.context)
})

