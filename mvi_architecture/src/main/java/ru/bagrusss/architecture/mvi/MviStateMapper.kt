package ru.bagrusss.architecture.mvi

import ru.bagrusss.architecture.mvi.common.ScreenStates

interface MviStateMapper<DOMAIN : ScreenStates.Domain, UI : ScreenStates.UI> {
    fun mapState(domainState: DOMAIN): UI
}