package ru.bagrusss.templateapp.architecture.mvi.navigation

import android.os.Parcelable
import io.reactivex.rxjava3.core.Observable

interface ResultNotifier {

    val resultsNotifications: Observable<Parcelable>

    fun notifyResult(result: Parcelable)

}