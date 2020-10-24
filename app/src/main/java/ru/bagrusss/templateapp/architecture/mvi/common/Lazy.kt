package ru.bagrusss.templateapp.architecture.mvi.common

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)