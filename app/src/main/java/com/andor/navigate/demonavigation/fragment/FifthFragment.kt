package com.andor.navigate.demonavigation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.andor.navigate.demonavigation.R


class FifthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fifth, container, false)
        val btn = view.findViewById<Button>(R.id.btn_fifthFragment)
        btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fifthFragment_to_sixthFragment)
        }
        return view
    }


}
