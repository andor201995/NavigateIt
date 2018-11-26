package com.andor.navigate.demonavigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_nested.*
import kotlinx.android.synthetic.main.activity_nested_final.*

class NestedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested)
        NavigationUI.setupActionBarWithNavController(this, NavHostFragment.findNavController(nested_nav_host))

    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.nested_nav_host
    ).navigateUp()

}
