package ru.bagrusss.architecture.mvvm

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ru.bagrusss.architecture.di.Injectable

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class MvvmActivity<DB : ViewDataBinding, out VM : BaseViewModel<*>> : AppCompatActivity(),
    Injectable {

    protected lateinit var binding: DB
    protected val vm by createViewModel()

    @get:LayoutRes
    protected abstract val layout: Int

    protected abstract fun createViewModel(): Lazy<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        lifecycle.addObserver(vm)
        inject()
    }

    override fun onDestroy() {
        lifecycle.removeObserver(vm)
        super.onDestroy()
    }

}