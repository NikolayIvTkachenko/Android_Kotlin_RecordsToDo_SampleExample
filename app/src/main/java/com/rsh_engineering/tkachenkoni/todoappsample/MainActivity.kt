package com.rsh_engineering.tkachenkoni.todoappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Created by Nikolay Tkachenko on 23, September, 2020
 *
 **/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.navHostFragment))

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navHostFragment)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
