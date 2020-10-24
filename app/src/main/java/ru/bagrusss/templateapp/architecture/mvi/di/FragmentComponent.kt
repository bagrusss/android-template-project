package ru.bagrusss.templateapp.architecture.mvi.di

import dagger.BindsInstance
import ru.bagrusss.templateapp.architecture.common.SchedulersProvider
import ru.bagrusss.templateapp.architecture.mvi.MviFragment
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultNotifier
import ru.bagrusss.templateapp.architecture.mvi.navigation.Router

interface FragmentComponent {

    val schedulersProvider: SchedulersProvider
    val resultNotifier: ResultNotifier
    val router: Router

    interface Builder<T : FragmentComponent, B> {

        @BindsInstance
        fun fragment(fragment: MviFragment<*, *, *>): B

        fun build(): T

    }

}