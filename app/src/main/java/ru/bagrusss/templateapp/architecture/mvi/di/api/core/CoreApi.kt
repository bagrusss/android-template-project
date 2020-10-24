package ru.bagrusss.templateapp.architecture.mvi.di.api.core

import ru.bagrusss.templateapp.architecture.common.SchedulersProvider
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultNotifier
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator

interface CoreApi {
    val schedulersProvider: SchedulersProvider

    val resultsMediator: ResultsMediator
    val resultsNotifier: ResultNotifier
}