package ru.bagrusss.templateapp.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class MvvmActivity<DB : ViewDataBinding, out VM : BaseViewModel<*>> : AppCompatActivity() {

    protected lateinit var binding: DB
    protected val vm by createViewModel()

    protected abstract val layout: Int
    protected abstract fun createViewModel(): Lazy<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        lifecycle.addObserver(vm)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(vm)
        super.onDestroy()
    }


}