package ru.bagrusss.templateapp.screens.mvi_example.ui.demo.di

import androidx.navigation.NavController
import dagger.BindsInstance
import dagger.Subcomponent
import ru.bagrusss.architecture.mvi.di.FragmentComponent
import ru.bagrusss.architecture.mvi.di.FragmentScope
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract

@FragmentScope
@Subcomponent(
    modules = [
        DemoModule::class
    ]
)
interface DemoFragmentComponent : FragmentComponent {

    val viewModel: DemoContract.ViewModel

    @Subcomponent.Builder
    interface Builder : FragmentComponent.Builder<DemoFragmentComponent, Builder> {

        @BindsInstance
        fun inputData(inputData: DemoContract.InputData): Builder

        @BindsInstance
        fun navController(navController: NavController): Builder

    }

}