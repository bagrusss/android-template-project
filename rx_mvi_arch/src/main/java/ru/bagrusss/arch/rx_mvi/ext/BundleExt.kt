package ru.bagrusss.arch.rx_mvi.ext

import android.os.Bundle
import ru.bagrusss.arch.rx_mvi.common.IOData

const val INPUT_KEY = "input_data"

fun <T : IOData.Input> Bundle?.toInput(): T = this?.getParcelable<T>(INPUT_KEY)!!

fun <T : IOData.Input> T.toBundle(): Bundle = Bundle().also { it.putParcelable(INPUT_KEY, this) }