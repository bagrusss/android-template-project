package ru.bagrusss.templateapp.architecture.mvi.di.api.core

import dagger.Binds
import dagger.Module
import ru.bagrusss.templateapp.architecture.common.SchedulersProvider
import ru.bagrusss.templateapp.architecture.common.SchedulersProviderImpl
import javax.inject.Singleton

@Module
internal interface CoreApiModule {

    @Binds
    @Singleton
    fun bindSchedulersProvider(impl: SchedulersProviderImpl): SchedulersProvider

}