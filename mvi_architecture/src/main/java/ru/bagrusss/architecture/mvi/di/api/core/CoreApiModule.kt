package ru.bagrusss.architecture.mvi.di.api.core

import dagger.Binds
import dagger.Module
import ru.bagrusss.architecture.common.SchedulersProvider
import ru.bagrusss.architecture.common.SchedulersProviderImpl
import ru.bagrusss.architecture.common.providers.ResourcesProvider
import ru.bagrusss.architecture.common.providers.ResourcesProviderImpl
import ru.bagrusss.architecture.mvi.navigation.BaseResultsMediator
import ru.bagrusss.architecture.mvi.navigation.ResultNotifier
import ru.bagrusss.architecture.mvi.navigation.ResultsMediator
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