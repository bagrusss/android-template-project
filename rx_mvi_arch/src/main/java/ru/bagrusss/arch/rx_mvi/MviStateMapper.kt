package ru.bagrusss.arch.rx_mvi

import ru.bagrusss.arch.rx_mvi.common.ScreenStates

interface MviStateMapper<DOMAIN : ScreenStates.Domain, UI : ScreenStates.UI> {
    fun mapState(domainState: DOMAIN): UI
}