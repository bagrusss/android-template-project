package ru.bagrusss.templateapp.screens.mvi_example.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgeResult(
    val age: Int
): Parcelable