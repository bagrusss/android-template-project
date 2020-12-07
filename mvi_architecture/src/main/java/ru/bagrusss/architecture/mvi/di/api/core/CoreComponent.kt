package ru.bagrusss.architecture.mvi.di.api.core

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
        fun create(): CoreApi
    }

}

object CoreApiProvider : LazySingletonHolder<CoreApi, Unit>({
    DaggerCoreComponent.factory()
        .create()
})

