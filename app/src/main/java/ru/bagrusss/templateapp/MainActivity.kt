package ru.bagrusss.templateapp

import ru.bagrusss.templateapp.databinding.ActivityMainBinding
import ru.bagrusss.templateapp.architecture.mvvm.EmptyViewModel
import ru.bagrusss.templateapp.architecture.mvvm.MvvmActivity

class MainActivity : MvvmActivity<ActivityMainBinding, EmptyViewModel>() {

    override val layout = R.layout.activity_main

    override fun createViewModel() = lazy(::EmptyViewModel)

    override fun inject() {

    }

}
