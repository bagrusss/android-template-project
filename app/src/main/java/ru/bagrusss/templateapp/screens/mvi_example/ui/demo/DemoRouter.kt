package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import androidx.navigation.NavController
import ru.bagrusss.templateapp.architecture.mvi.navigation.BaseNavRouter
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator
import ru.bagrusss.templateapp.screens.mvi_example.domain.AgeResult
import javax.inject.Inject

internal class DemoRouter @Inject constructor(
    resultsMediator: ResultsMediator,
    private val navController: NavController
) : BaseNavRouter(resultsMediator), DemoContract.Router {

    override fun openResultScreen(onResult: (AgeResult) -> Unit) {
        resultsMediator.results
            .ofType(AgeResult::class.java)
            .doOnNext(onResult)
    }
}