package ru.bagrusss.architecture.mvi.di.api.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.bagrusss.architecture.mvi.common.LazySingletonHolder
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

