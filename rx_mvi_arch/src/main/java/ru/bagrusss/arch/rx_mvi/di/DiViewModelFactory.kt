package ru.bagrusss.arch.rx_mvi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

abstract class DiViewModelFactory<VM : ViewModel>(private val provider: Provider<VM>) : ViewModelProvider.Factory {

    override fun <VM : ViewModel> create(modelClass: Class<VM>) = provider.get() as VM

}