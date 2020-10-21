package ru.bagrusss.templateapp.architecture.mvi.di.api.core

import ru.bagrusss.templateapp.architecture.common.SchedulersProvider

interface CoreApi {
    val schedulersProvider: SchedulersProvider
}