package ru.bagrusss.templateapp.architecture.mvi.navigation

import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseRouter(
    protected val resultsMediator: ResultsMediator
) : Router {

    private var resultDisposable = Disposable.empty()

    override fun onStart() {
        resultDisposable = resultsMediator.results
            .subscribe()
    }

    override fun onStop() {
        resultDisposable.dispose()
    }

}