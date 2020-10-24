package ru.bagrusss.templateapp.screens.mvi_example.ui.demo.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bagrusss.templateapp.architecture.mvi.MviStateMapper
import ru.bagrusss.templateapp.architecture.mvi.di.BaseFragmentModule
import ru.bagrusss.templateapp.architecture.mvi.di.DiViewModelFactory
import ru.bagrusss.templateapp.architecture.mvi.di.FragmentQualifier
import ru.bagrusss.templateapp.architecture.mvi.di.FragmentScope
import ru.bagrusss.templateapp.architecture.mvi.navigation.Router
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract.*
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoRouterImpl
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoStateMapper
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoViewModel
import javax.inject.Inject
import javax.inject.Provider


@Module
internal abstract class DemoModule : BaseFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindViewModeFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @FragmentScope
    abstract fun bindStateMapper(stateMapper: DemoStateMapper): MviStateMapper<DomainState, UIState>

    @Binds
    @FragmentScope
    abstract fun bindDemoRouter(router: DemoRouterImpl): DemoRouter

    @Binds
    @FragmentScope
    abstract fun bindRouter(demoRouter: DemoRouter): Router

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideViewModelProvider(
            @FragmentQualifier storeOwner: ViewModelStoreOwner,
            factory: ViewModelProvider.Factory
        ) = ViewModelProvider(storeOwner, factory)

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideViewModel(viewModelProvider: ViewModelProvider): ViewModel = viewModelProvider[DemoViewModel::class.java]

    }

}

internal class ViewModelFactory @Inject constructor(
    provider: Provider<DemoViewModel>
) : DiViewModelFactory<DemoViewModel>(provider)
