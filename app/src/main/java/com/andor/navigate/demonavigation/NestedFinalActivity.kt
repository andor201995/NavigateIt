package com.andor.navigate.demonavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_nested_final.*

class NestedFinalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_final)
        NavigationUI.setupActionBarWithNavController(this, NavHostFragment.findNavController(final_nested_nav_host))

    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.final_nested_nav_host
    ).navigateUp()
}
