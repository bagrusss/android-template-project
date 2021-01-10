package ru.bagrusss.arch.common

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {

    val io: Scheduler
    val computation: Scheduler
    val main: Scheduler

}