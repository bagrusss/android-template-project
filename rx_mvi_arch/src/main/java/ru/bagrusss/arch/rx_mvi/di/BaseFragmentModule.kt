package ru.bagrusss.arch.rx_mvi.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.Binds
import dagger.Module
import ru.bagrusss.arch.rx_mvi.MviFragment

@Module
interface BaseFragmentModule {

    @Binds
    @FragmentScope
    @FragmentQualifier
    fun provideViewStoreOwner(screen: MviFragment<*, *, *>): ViewModelStoreOwner

}