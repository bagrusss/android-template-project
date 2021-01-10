package ru.bagrusss.architecture.mvi.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface IOData {

    interface Input : Parcelable

    interface Output : Parcelable

    @Parcelize
    object EmptyInput : Input

    @Parcelize
    object EmptyOutput : Output

}