package ru.bagrusss.templateapp.architecture.di

/**
 * Created by bagrusss on 19.01.2020
 */
interface DiComponent<I> {

    fun inject(injectable: I)

}