package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import ru.bagrusss.architecture.mvi.MviStateMapper
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.*
import javax.inject.Inject

internal class DemoStateMapper @Inject constructor(

): MviStateMapper<DomainState, UIState> {

    override fun mapState(domainState: DomainState) = UIState(domainState.amount.toString())

}