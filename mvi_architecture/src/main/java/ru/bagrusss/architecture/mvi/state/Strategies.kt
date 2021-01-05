package ru.bagrusss.architecture.mvi.state

import io.reactivex.rxjava3.core.ObservableTransformer

object NonRepeatableStrategy : StateObserverStrategy {

    override fun <T> getTransformer(): ObservableTransformer<T, T> = ObservableTransformer { it.distinctUntilChanged() }
}

object DummyStrategy : StateObserverStrategy {

    override fun <T> getTransformer(): ObservableTransformer<T, T> = ObservableTransformer { it }
}