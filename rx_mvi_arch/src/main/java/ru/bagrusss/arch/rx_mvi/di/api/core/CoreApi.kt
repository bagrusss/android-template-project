package ru.bagrusss.arch.rx_mvi.di.api.core

import ru.bagrusss.arch.common.SchedulersProvider
import ru.bagrusss.arch.common.providers.ResourcesProvider
import ru.bagrusss.arch.rx_mvi.navigation.ResultNotifier
import ru.bagrusss.arch.rx_mvi.navigation.ResultsMediator

interface CoreApi {
    val schedulersProvider: SchedulersProvider
    val resourcesProvider: ResourcesProvider

    val resultsMediator: ResultsMediator
    val resultsNotifier: ResultNotifier
}