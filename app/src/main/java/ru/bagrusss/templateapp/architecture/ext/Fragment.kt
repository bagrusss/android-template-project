package ru.bagrusss.templateapp.architecture.ext

import ru.bagrusss.templateapp.architecture.mvi.MviFragment
import ru.bagrusss.templateapp.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.architecture.mvi.ext.toBundle

fun <T : IOData.Input> MviFragment<*, T, *>.withInput(input: T) = also { it.arguments = input.toBundle() }