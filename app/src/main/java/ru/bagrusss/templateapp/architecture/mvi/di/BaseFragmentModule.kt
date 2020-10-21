package ru.bagrusss.templateapp.architecture.mvi.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.Binds
import dagger.Module
import ru.bagrusss.templateapp.architecture.mvi.MviFragment
import ru.bagrusss.templateapp.architecture.mvi.navigation.BaseResultsMediator
import ru.bagrusss.templateapp.architecture.mvi.navigation.ResultsMediator
import javax.inject.Singleton

@Module
interface BaseFragmentModule {

    @Binds
    @FragmentScope
    @FragmentQualifier
    fun provideViewStoreOwner(screen: MviFragment<*, *, *>): ViewModelStoreOwner

}