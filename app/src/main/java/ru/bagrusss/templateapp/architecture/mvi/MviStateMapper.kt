package ru.bagrusss.templateapp.architecture.mvi

import ru.bagrusss.templateapp.architecture.mvi.common.ScreenStates

interface MviStateMapper<DOMAIN : ScreenStates.Domain, UI : ScreenStates.UI> {
    fun mapState(domainState: DOMAIN): UI
}