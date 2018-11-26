package com.andor.navigate.demonavigation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.andor.navigate.demonavigation.R

class SixthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sixth, container, false)
        val btn = view.findViewById<Button>(R.id.btn_sixthFragment)
        btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_global_final_nested_nav_graph)
        }
        return view
    }


}
