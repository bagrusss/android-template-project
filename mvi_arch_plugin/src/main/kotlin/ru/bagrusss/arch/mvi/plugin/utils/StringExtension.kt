package ru.bagrusss.arch.mvi.plugin.utils

fun String.camelToSnakeCase() = fold(StringBuilder(length)) { acc, c ->
    if (c in 'A'..'Z') (if (acc.isNotEmpty()) acc.append('_') else acc).append(c + ('a' - 'A'))
    else acc.append(c)
}.toString()