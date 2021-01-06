package ru.bagrusss.architecture.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface LifecycleHandler : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy()
    
}