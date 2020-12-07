package ru.bagrusss.architecture.mvi.di

import dagger.BindsInstance
import ru.bagrusss.architecture.common.SchedulersProvider
import ru.bagrusss.architecture.mvi.MviFragment
import ru.bagrusss.architecture.mvi.navigation.NavRouter
import ru.bagrusss.architecture.mvi.navigation.ResultNotifier

interface FragmentComponent {

    val schedulersProvider: SchedulersProvider
    val resultNotifier: ResultNotifier
    val router: NavRouter

    interface Builder<T : FragmentComponent, B> {

        @BindsInstance
        fun fragment(fragment: MviFragment<*, *, *>): B

        fun build(): T

    }

}