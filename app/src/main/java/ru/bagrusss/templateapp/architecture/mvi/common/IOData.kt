package ru.bagrusss.templateapp.architecture.mvi.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface IOData {

    interface Input : Parcelable

    interface Output : Parcelable

    @Parcelize
    object EmptyInput : Input

    @Parcelize
    object EmptyOutput : Output

}