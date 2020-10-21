package ru.bagrusss.templateapp.architecture.mvi.navigation

import android.os.Parcelable
import io.reactivex.rxjava3.core.Observable

interface ResultsMediator {

    val results: Observable<Parcelable>

    fun postResult(result: Parcelable)

}