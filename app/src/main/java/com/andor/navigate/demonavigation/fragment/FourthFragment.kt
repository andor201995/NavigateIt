package com.andor.navigate.demonavigation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

import com.andor.navigate.demonavigation.R

class FourthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)
        val btn = view.findViewById<Button>(R.id.btn_fourthFragment)
        btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fourthFragment_to_fifthFragment)
        }
        return view
    }


}
