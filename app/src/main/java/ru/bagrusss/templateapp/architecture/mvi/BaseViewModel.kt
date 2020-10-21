package ru.bagrusss.templateapp.architecture.mvi

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ru.bagrusss.templateapp.architecture.common.SchedulersProvider
import ru.bagrusss.templateapp.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.architecture.mvi.common.ScreenStates
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<STATE : ScreenStates.Domain, UI : ScreenStates.UI, OUTPUT : IOData.Output>(
    private val stateMapper: MviStateMapper<STATE, UI>,
    private val resultsMediator: ResultsMediator,
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

    protected fun postResult(result: OUTPUT) = resultsMediator.postResult(result)

}