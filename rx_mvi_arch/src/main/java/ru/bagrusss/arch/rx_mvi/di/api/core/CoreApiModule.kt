package ru.bagrusss.arch.rx_mvi.di.api.core

import dagger.Binds
import dagger.Module
import ru.bagrusss.arch.common.SchedulersProvider
import ru.bagrusss.arch.common.SchedulersProviderImpl
import ru.bagrusss.arch.common.providers.ResourcesProvider
import ru.bagrusss.arch.common.providers.ResourcesProviderImpl
import ru.bagrusss.arch.rx_mvi.navigation.BaseResultsMediator
import ru.bagrusss.arch.rx_mvi.navigation.ResultNotifier
import ru.bagrusss.arch.rx_mvi.navigation.ResultsMediator
import javax.inject.Singleton

@Module
internal interface CoreApiModule {

    @Binds
    @Singleton
    fun bindSchedulersProvider(impl: SchedulersProviderImpl): SchedulersProvider

    @Binds
    @Singleton
    fun bindResourcesProvider(impl: ResourcesProviderImpl): ResourcesProvider

    @Binds
    @Singleton
    fun bindResultsMediator(resultsMediator: BaseResultsMediator): ResultsMediator

    @Binds
    @Singleton
    fun bindResultsNotifier(resultsMediator: BaseResultsMediator): ResultNotifier

}