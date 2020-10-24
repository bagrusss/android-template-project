package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import kotlinx.android.parcel.Parcelize
import ru.bagrusss.templateapp.architecture.mvi.MviViewModel
import ru.bagrusss.templateapp.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.architecture.mvi.common.ScreenStates
import ru.bagrusss.templateapp.architecture.mvi.navigation.Router
import ru.bagrusss.templateapp.screens.mvi_example.domain.AgeResult

interface DemoContract {

    interface ViewModel : MviViewModel<UIState, IOData.EmptyOutput> {
        fun startAgeClicked()
    }

    @Parcelize
    data class InputData(
        val amount: Int
    ) : IOData.Input

    data class DomainState(
        val amount: Int
    ) : ScreenStates.Domain

    data class UIState(
        val amount: String
    ) : ScreenStates.UI

    interface DemoRouter : Router {
        fun openResultScreen(onResult: (AgeResult) -> Unit)
    }

}