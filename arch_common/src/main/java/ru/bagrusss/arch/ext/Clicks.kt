package ru.bagrusss.arch.ext

import android.view.View
import android.view.ViewConfiguration
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun View.throttledClicks(): Observable<Unit> = clicks().throttleLast(ViewConfiguration.getDoubleTapTimeout().toLong(), TimeUnit.MILLISECONDS)