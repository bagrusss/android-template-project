package ru.bagrusss.architecture.mvi.di.api.core

import ru.bagrusss.architecture.common.SchedulersProvider
import ru.bagrusss.architecture.mvi.navigation.ResultNotifier
import ru.bagrusss.architecture.mvi.navigation.ResultsMediator

interface CoreApi {
    val schedulersProvider: SchedulersProvider

    val resultsMediator: ResultsMediator
    val resultsNotifier: ResultNotifier
}