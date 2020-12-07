package ru.bagrusss.architecture.mvi.common

interface ScreenStates {

    interface Domain

    interface UI {
        fun calculatePayload(oldState: UI): UIPayload? = null
    }

    interface UIPayload

    object EmptyDomain : Domain

    object EmptyUI : UI

}