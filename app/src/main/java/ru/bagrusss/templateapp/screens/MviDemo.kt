package ru.bagrusss.templateapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bagrusss.templateapp.R
import ru.bagrusss.templateapp.architecture.mvi.ext.toBundle
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoFragment

class MviDemo: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_container)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DemoFragment().apply {
                arguments = DemoContract.InputData(10).toBundle()
            })
            .commit()
    }
}