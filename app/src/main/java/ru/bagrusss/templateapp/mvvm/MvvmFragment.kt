package ru.bagrusss.templateapp.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Created by bagrusss on 12.08.2019
 */
abstract class MvvmFragment<B : ViewDataBinding, out VM : BaseViewModel<*>> : Fragment() {

    protected lateinit var binding: B
    protected val vm by createViewModel()

    protected abstract val layout: Int
    protected abstract fun createViewModel(): Lazy<VM>

    protected open var isFullScreen = false

    protected val mainScope by lazy { CoroutineScope(Dispatchers.Main) }

    protected val appCompatActivity: AppCompatActivity?
        get() = activity as AppCompatActivity?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<B>(inflater, layout, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isFullScreen) {
            view.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        lifecycle.addObserver(vm)
    }


    override fun onDestroyView() {
        mainScope.cancel()
        lifecycle.removeObserver(vm)
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}