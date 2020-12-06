package ru.bagrusss.templateapp

import ru.bagrusss.architecture.mvvm.EmptyViewModel
import ru.bagrusss.architecture.mvvm.MvvmActivity
import ru.bagrusss.templateapp.databinding.ActivityMainBinding

class MainActivity : MvvmActivity<ActivityMainBinding, EmptyViewModel>() {

    override val layout = R.layout.activity_main

    override fun createViewModel() = lazy(::EmptyViewModel)

    override fun inject() {

    }

}
