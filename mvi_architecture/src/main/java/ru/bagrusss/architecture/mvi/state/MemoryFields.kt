package ru.bagrusss.architecture.mvi.state

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

open class MemoryField<T : Any> : StateField<T> {

    @Volatile
    private lateinit var value: T
    protected val serializedSubject: Subject<T>

    constructor() {
        serializedSubject = PublishSubject.create<T>().toSerialized()
    }

    constructor(default: T) {
        value = default
        serializedSubject = PublishSubject.create<T>().toSerialized()
    }

    override fun isInitialized(): Boolean = ::value.isInitialized

    override fun get(): T = value

    override fun set(value: T) {
        this.value = value
        serializedSubject.onNext(value)
    }

    override fun observe(): Observable<T> = observe(NonRepeatableStrategy)

    override fun update(value: T): Observable<T> {
        return Observable.fromCallable {
            set(value)
            value
        }
    }

    override fun observe(strategy: StateObserverStrategy): Observable<T> {
        return Observable.defer {
            if (isInitialized()) serializedSubject.startWithItem(get()) else serializedSubject
        }
            .compose(strategy.getTransformer())
    }
}