package ru.bagrusss.arch.common

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SchedulersProviderImpl @Inject constructor(): SchedulersProvider {

    override val io: Scheduler
        get() = Schedulers.io()

    override val computation: Scheduler
        get() = Schedulers.computation()

    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()

}