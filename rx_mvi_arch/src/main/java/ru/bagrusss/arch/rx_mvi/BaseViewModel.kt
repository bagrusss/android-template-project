package ru.bagrusss.arch.rx_mvi

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.bagrusss.arch.common.SchedulersProvider
import ru.bagrusss.arch.common.unsafeLazy
import ru.bagrusss.arch.rx_mvi.common.IOData
import ru.bagrusss.arch.rx_mvi.common.ScreenStates
import ru.bagrusss.arch.rx_mvi.navigation.ResultsMediator
import ru.bagrusss.arch.rx_mvi.state.MemoryField
import ru.bagrusss.arch.rx_mvi.state.StateField
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<STATE : ScreenStates.Domain, UI : ScreenStates.UI, OUTPUT : IOData.Output>(
    private val stateMapper: MviStateMapper<STATE, UI>,
    protected val schedulers: SchedulersProvider
) : ViewModel(), MviViewModel<UI, OUTPUT> {

    private val firstEmit = AtomicBoolean(true)

    private val destroyDisposables by unsafeLazy(::CompositeDisposable)

    private val stopDisposables by unsafeLazy(::CompositeDisposable)

    abstract fun observeDomainState(): Observable<STATE>

    override fun create() {}

    override fun start() {}

    @CallSuper
    override fun stop() {
        stopDisposables.clear()
    }

    @CallSuper
    override fun destroy() {
        destroyDisposables.clear()
    }

    override fun stateChanges(): Observable<UI> = observeDomainState()
        .compose { observable ->
            if (firstEmit.get()) {
                firstEmit.set(false)
                observable
            } else {
                observable.observeOn(schedulers.computation)
            }
        }
        .map(stateMapper::mapState)

    protected fun <T : Any> createStateProperty(): StateField<T> = MemoryField()

    protected fun <T : Any> createStateProperty(default: T): StateField<T> = MemoryField(default)

    protected fun ResultsMediator.postResult(result: OUTPUT) = postResult(result)

    private fun <T> Observable<T>.subscribeTill(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {},
    ) = observeOn(schedulers.main)
        .subscribe(onNext, onError, onComplete)

    protected fun <T> Observable<T>.subscrubeTillDestroy(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {},
    ) = subscribeTill(onError, onComplete, onNext).also {
        destroyDisposables += it
    }

    protected fun <T> Observable<T>.subscrubeTillStop(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {},
    ) = subscribeTill(onError, onComplete, onNext).also {
        stopDisposables += it
    }

    private fun <T> Single<T>.subscribeTill(
        onError: (Throwable) -> Unit = Timber::e,
        onSuccess: (T) -> Unit = {},
    ) = observeOn(schedulers.main)
        .subscribe(onSuccess, onError)

    protected fun <T> Single<T>.subscrubeTillDestroy(
        onError: (Throwable) -> Unit = Timber::e,
        onSuccess: (T) -> Unit = {},
    ) = subscribeTill(onError, onSuccess).also {
        destroyDisposables += it
    }

    protected fun <T> Single<T>.subscrubeTillStop(
        onError: (Throwable) -> Unit = Timber::e,
        onSuccess: (T) -> Unit = {},
    ) = subscribeTill(onError, onSuccess).also {
        stopDisposables += it
    }

    private fun Completable.subscribeTill(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
    ) = observeOn(schedulers.main)
        .subscribe(onComplete, onError)

    protected fun Completable.subscrubeTillDestroy(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
    ) = subscribeTill(onError, onComplete).also {
        destroyDisposables += it
    }

    protected fun Completable.subscrubeTillStop(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
    ) = subscribeTill(onError, onComplete).also {
        stopDisposables += it
    }

}