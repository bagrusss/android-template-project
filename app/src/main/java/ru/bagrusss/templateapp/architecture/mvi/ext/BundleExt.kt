package ru.bagrusss.templateapp.architecture.mvi.ext

import android.os.Bundle
import ru.bagrusss.templateapp.architecture.mvi.common.IOData

private const val INPUT_KEY = "input_data"

fun <T : IOData.Input> Bundle?.toInput(): T = this?.getParcelable<T>(INPUT_KEY)!!

fun <T : IOData.Input> T.toBundle(): Bundle = Bundle().also { it.putParcelable(INPUT_KEY, this) }