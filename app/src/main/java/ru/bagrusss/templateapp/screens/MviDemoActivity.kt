package ru.bagrusss.templateapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import ru.bagrusss.templateapp.R
import ru.bagrusss.templateapp.architecture.mvi.ext.toBundle
import ru.bagrusss.templateapp.screens.mvi_example.ui.demo.DemoContract

class MviDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_container)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHostFragment
            .navController
            .setGraph(R.navigation.nav_graph, DemoContract.InputData(10).toBundle())
    }
}