package ru.bagrusss.architecture.mvi.navigation

import android.os.Parcelable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class BaseResultsMediator @Inject constructor(): ResultsMediator, ResultNotifier {

    private val notificationsSubject = PublishSubject.create<Parcelable>()
    private val resultSubject = PublishSubject.create<Parcelable>()

    override val resultsNotifications: Observable<Parcelable>
        get() = notificationsSubject.hide()

    override val results: Observable<Parcelable>
        get() = resultSubject.hide()

    override fun notifyResult(result: Parcelable) = resultSubject.onNext(result)

    override fun postResult(result: Parcelable) = notificationsSubject.onNext(result)

}