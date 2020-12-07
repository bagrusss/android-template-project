package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import io.reactivex.rxjava3.core.Observable
import ru.bagrusss.architecture.common.SchedulersProvider
import ru.bagrusss.architecture.mvi.BaseViewModel
import ru.bagrusss.architecture.mvi.MviStateMapper
import ru.bagrusss.architecture.mvi.common.IOData
import ru.bagrusss.architecture.mvi.navigation.ResultsMediator
import javax.inject.Inject

import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.*
import timber.log.Timber

internal class DemoViewModel @Inject constructor(
    stateMapper: MviStateMapper<DomainState, UIState>,
    resultsMediator: ResultsMediator,
    schedulersProvider: SchedulersProvider,
    private val inputData: InputData,
    private val router: Router
) : BaseViewModel<DomainState, UIState, IOData.EmptyOutput>(stateMapper, resultsMediator, schedulersProvider), ViewModel {

    override fun observeDomainState(): Observable<DomainState> = Observable.just(DomainState(inputData.amount))

    override fun startAgeClicked() {
        router.openResultScreen {
            Timber.d("result $it")
        }
    }

}