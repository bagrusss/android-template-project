package ru.bagrusss.architecture.mvi.di

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
annotation class FragmentScope

@Scope
annotation class ModuleScope


@Qualifier
annotation class FragmentQualifier