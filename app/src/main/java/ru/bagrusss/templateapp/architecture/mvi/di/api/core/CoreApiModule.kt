package ru.bagrusss.templateapp.architecture.mvi.di.api.core

import dagger.Binds
import dagger.Module
import ru.bagrusss.templateapp.architecture.common.SchedulersProvider
import ru.bagrusss.templateapp.architecture.common.SchedulersProviderImpl
import ru.bagrusss.templateapp.architecture.mvi.navigation.BaseResultsMediator
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultNotifier
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator
import javax.inject.Singleton

@Module
internal interface CoreApiModule {

    @Binds
    @Singleton
    fun bindSchedulersProvider(impl: SchedulersProviderImpl): SchedulersProvider

    @Binds
    @Singleton
    fun bindResultsMediator(resultsMediator: BaseResultsMediator): ResultsMediator

    @Binds
    @Singleton
    fun bindResultsNotifier(resultsMediator: BaseResultsMediator): ResultNotifier

}