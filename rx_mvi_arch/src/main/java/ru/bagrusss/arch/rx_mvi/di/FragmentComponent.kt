package ru.bagrusss.arch.rx_mvi.di

import dagger.BindsInstance
import ru.bagrusss.arch.common.SchedulersProvider
import ru.bagrusss.arch.rx_mvi.MviFragment
import ru.bagrusss.arch.rx_mvi.navigation.ResultNotifier

interface FragmentComponent {

    val schedulersProvider: SchedulersProvider
    val resultNotifier: ResultNotifier

    interface Builder<T : FragmentComponent, B> {

        @BindsInstance
        fun fragment(fragment: MviFragment<*, *, *>): B

        fun build(): T

    }

}