package ru.bagrusss.architecture.mvi

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ru.bagrusss.architecture.common.SchedulersProvider
import ru.bagrusss.architecture.mvi.common.IOData
import ru.bagrusss.architecture.mvi.common.ScreenStates
import ru.bagrusss.architecture.mvi.navigation.ResultsMediator
import ru.bagrusss.architecture.mvi.state.MemoryField
import ru.bagrusss.architecture.mvi.state.StateField
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<STATE : ScreenStates.Domain, UI : ScreenStates.UI, OUTPUT : IOData.Output>(
    private val stateMapper: MviStateMapper<STATE, UI>,
    private val schedulersProvider: SchedulersProvider
) : ViewModel(), MviViewModel<UI, OUTPUT> {

    private val firstEmit = AtomicBoolean(true)

    abstract fun observeDomainState(): Observable<STATE>

    override fun stateChanges(): Observable<UI> = observeDomainState()
        .compose { observable ->
            if (firstEmit.get()) {
                firstEmit.set(false)
                observable
            } else {
                observable.observeOn(schedulersProvider.computation)
            }
        }
        .map(stateMapper::mapState)

    protected fun <T : Any> createStateProperty(): StateField<T> = MemoryField()

    protected fun <T : Any> createStateProperty(default: T): StateField<T> = MemoryField(default)

    protected fun ResultsMediator.postResult(result: OUTPUT) = postResult(result)

}