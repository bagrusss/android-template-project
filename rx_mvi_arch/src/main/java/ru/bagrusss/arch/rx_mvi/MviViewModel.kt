package ru.bagrusss.arch.rx_mvi

import io.reactivex.rxjava3.core.Observable
import ru.bagrusss.arch.rx_mvi.common.IOData
import ru.bagrusss.arch.rx_mvi.common.ScreenStates

interface MviViewModel<UI : ScreenStates.UI, OUTPUT : IOData.Output>: LifecycleHandler {

    fun stateChanges(): Observable<UI>

}