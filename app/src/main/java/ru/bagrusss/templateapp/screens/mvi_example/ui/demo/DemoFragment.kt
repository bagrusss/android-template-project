package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import kotlinx.android.synthetic.main.fragment_demo.*
import ru.bagrusss.templateapp.R
import ru.bagrusss.templateapp.architecture.mvi.MviFragment
import ru.bagrusss.templateapp.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.architecture.mvi.common.unsafeLazy
import ru.bagrusss.templateapp.architecture.mvi.ext.throttledClicks
import ru.bagrusss.templateapp.screens.mvi_example.di.DemoModuleProvider
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.InputData
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.UIState
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.di.DemoFragmentComponent


class DemoFragment : MviFragment<UIState, InputData, IOData.EmptyOutput>(R.layout.fragment_demo) {

    override val viewModel by unsafeLazy { component.viewModel }

    override val component: DemoFragmentComponent by unsafeLazy {
        DemoModuleProvider.component
            .demoComponentBuilder
            .inputData(inputData)
            .fragment(this)
            .build()
    }

    override fun onStart() {
        super.onStart()

        click_me.throttledClicks()
            .subscribeTillStop(onNext = { viewModel.startAgeClicked() })
    }

    override fun buildScreen(state: UIState) {
        click_me.text = state.amount
    }

}