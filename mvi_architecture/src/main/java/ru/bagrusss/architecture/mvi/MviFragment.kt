package ru.bagrusss.architecture.mvi

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.bagrusss.architecture.mvi.common.IOData
import ru.bagrusss.architecture.mvi.common.ScreenStates
import ru.bagrusss.architecture.common.unsafeLazy
import ru.bagrusss.architecture.mvi.di.FragmentComponent
import ru.bagrusss.architecture.mvi.ext.toInput
import timber.log.Timber

abstract class MviFragment<UI : ScreenStates.UI, INPUT_DATA : IOData.Input, OUTPUT_DATA : IOData.Output>(layout: Int) : Fragment(layout) {

    protected abstract val component: FragmentComponent

    protected abstract val viewModel: MviViewModel<UI, OUTPUT_DATA>

    protected val inputData: INPUT_DATA
        get() = arguments.toInput()

    private val destroyFragmentDisposable by unsafeLazy(::CompositeDisposable)
    private val stopFragmentDisposable by unsafeLazy(::CompositeDisposable)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateChanges()
            .observeOn(component.schedulersProvider.main)
            .subscribeTillDestroy(onNext = ::buildScreen)

        component.resultNotifier
            .resultsNotifications
            .subscribeTillDestroy(onNext = ::setResult)

        setFragmentResultListener(RESULT_KEY) { key, bundle ->
            bundle.getParcelable<Parcelable>(RESULT_KEY)?.let(component.resultNotifier::notifyResult)
        }
    }

    override fun onStop() {
        super.onStop()

        stopFragmentDisposable.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        destroyFragmentDisposable.clear()
    }

    protected open fun buildScreen(state: UI) = Unit

    protected fun <T> Observable<T>.subscribeTillDestroy(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {}
    ) {
        destroyFragmentDisposable += subscribe(onNext, onError, onComplete)
    }

    protected fun <T> Observable<T>.subscribeTillStop(
        onError: (Throwable) -> Unit = Timber::e,
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {}
    ) {
        stopFragmentDisposable += subscribe(onNext, onError, onComplete)
    }

    private fun setResult(result: Parcelable) {
        val resultBundle = Bundle().apply {
            putParcelable(RESULT_KEY, result)
        }
        setFragmentResult(RESULT_KEY, resultBundle)
    }

    companion object {
        private const val RESULT_KEY = "RESULT_KEY"
    }

}