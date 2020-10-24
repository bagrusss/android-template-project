package ru.bagrusss.templateapp.architecture.mvi.navigation

import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseNavRouter(
    protected val resultsMediator: ResultsMediator
) : NavRouter {

    private var resultDisposable = Disposable.empty()

    override fun onStart() {
        resultDisposable = resultsMediator.results
            .subscribe()
    }

    override fun onStop() {
        resultDisposable.dispose()
    }

}