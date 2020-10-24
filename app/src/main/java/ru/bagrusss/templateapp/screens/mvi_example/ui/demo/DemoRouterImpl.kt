package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import ru.bagrusss.templateapp.architecture.mvi.navigation.BaseRouter
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator
import ru.bagrusss.templateapp.screens.mvi_example.domain.AgeResult
import javax.inject.Inject

internal class DemoRouterImpl @Inject constructor(
    resultsMediator: ResultsMediator
): BaseRouter(resultsMediator), DemoContract.DemoRouter {

    override fun openResultScreen(onResult: (AgeResult) -> Unit) {
        resultsMediator.results
            .doOnNext {
                if (it is AgeResult) {
                    onResult(it)
                }
            }
    }

}