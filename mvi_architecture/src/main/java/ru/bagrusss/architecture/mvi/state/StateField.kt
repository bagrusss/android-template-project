package ru.bagrusss.architecture.mvi.state

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

interface StateField<T : Any> : ImmutableStateField<T> {

    fun set(value: T)

    fun update(value: T): Observable<T>

}

interface ImmutableStateField<T : Any> {

    fun isInitialized(): Boolean

    fun get(): T

    fun observe(): Observable<T>

    fun observe(strategy: StateObserverStrategy): Observable<T>

    operator fun invoke() = get()
}

interface StateObserverStrategy {

    fun <T> getTransformer(): ObservableTransformer<T, T>
}