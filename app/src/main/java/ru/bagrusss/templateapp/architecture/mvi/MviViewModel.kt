package ru.bagrusss.templateapp.architecture.mvi

import io.reactivex.rxjava3.core.Observable
import ru.bagrusss.templateapp.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.architecture.mvi.common.ScreenStates

interface MviViewModel<UI : ScreenStates.UI, OUTPUT : IOData.Output> {

    fun stateChanges(): Observable<UI>

}