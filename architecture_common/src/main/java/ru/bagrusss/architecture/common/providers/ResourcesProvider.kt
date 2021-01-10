package ru.bagrusss.architecture.common.providers

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: Any): String

}