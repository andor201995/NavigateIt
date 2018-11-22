package com.andor.navigate.demonavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import com.andor.navigate.demonavigation.R

class NestedFinalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_final)
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.nav_host
    ).navigateUp()
}
