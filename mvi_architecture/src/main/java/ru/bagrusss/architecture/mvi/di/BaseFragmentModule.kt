package ru.bagrusss.architecture.mvi.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.Binds
import dagger.Module
import ru.bagrusss.architecture.mvi.MviFragment

@Module
interface BaseFragmentModule {

    @Binds
    @FragmentScope
    @FragmentQualifier
    fun provideViewStoreOwner(screen: MviFragment<*, *, *>): ViewModelStoreOwner

}