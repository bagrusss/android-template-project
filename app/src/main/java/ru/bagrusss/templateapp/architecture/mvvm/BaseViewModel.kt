package ru.bagrusss.templateapp.architecture.mvvm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class BaseViewModel<I : Interactor>(
    @JvmField protected val interactor: I
) : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun created() {}

}