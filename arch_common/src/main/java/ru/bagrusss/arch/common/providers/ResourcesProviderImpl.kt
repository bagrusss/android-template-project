package ru.bagrusss.arch.common.providers

import android.content.Context
import javax.inject.Inject

class ResourcesProviderImpl @Inject constructor(
    private val context: Context
): ResourcesProvider {

    override fun getString(resId: Int) = context.getString(resId)

    override fun getString(resId: Int, vararg args: Any) = context.getString(resId, args)

}