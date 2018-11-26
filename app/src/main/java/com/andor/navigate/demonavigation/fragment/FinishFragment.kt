package com.andor.navigate.demonavigation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andor.navigate.demonavigation.R


class FinishFragment : Fragment() {

    val bottomMenuFragment = BottomMenuFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_finish, container, false)
        val btn = view.findViewById<Button>(R.id.btn_bottomSheet)
        btn.setOnClickListener {
            if (!bottomMenuFragment.isAdded) {
                bottomMenuFragment.show(activity!!.supportFragmentManager, "Bottom_Sheet")
            }
        }
        return view
    }


}
