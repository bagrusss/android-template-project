package ru.bagrusss.templateapp.screens.mvi_example.ui.demo

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_demo.*
import ru.bagrusss.architecture.common.unsafeLazy
import ru.bagrusss.architecture.ext.throttledClicks
import ru.bagrusss.architecture.mvi.MviFragment
import ru.bagrusss.architecture.mvi.common.IOData
import ru.bagrusss.templateapp.R
import ru.bagrusss.templateapp.screens.mvi_example.di.DemoModuleProvider
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.InputData
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.UIState


class DemoFragment : MviFragment<UIState, InputData, IOData.EmptyOutput>(R.layout.fragment_demo) {

    override val viewModel by unsafeLazy { component.viewModel }

    override val component by unsafeLazy {
        DemoModuleProvider.component
            .demoComponentBuilder
            .inputData(inputData)
            .navController(findNavController())
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