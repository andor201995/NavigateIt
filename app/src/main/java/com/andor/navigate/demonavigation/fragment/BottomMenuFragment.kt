package com.andor.navigate.demonavigation.fragment


import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.andor.navigate.demonavigation.R


class BottomMenuFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(R.id.bottom_sheet_nav_host_container, BottomSheetNavFragment(), "Bottom_sheet_Fragment")
            .addToBackStack("Bottom_sheet_Fragment").commit()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : BottomSheetDialog(activity!!, theme) {
            override fun onBackPressed() {
                val fragment = childFragmentManager.fragments[0]
                val navigateUp = Navigation.findNavController(fragment.view!!).navigateUp()
                if (!navigateUp) {
                    dismiss()
                }

            }
        }
//        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnKeyListener { dialogInterface, keyCode, kEvent ->
//            if (keyCode == KeyEvent.KEYCODE_BACK && kEvent.action == KeyEvent.ACTION_UP) {
//                val fragment = childFragmentManager.fragments[0]
//                val navigateUp = Navigation.findNavController(fragment.view!!).navigateUp()
//                if (!navigateUp) {
//                    dialogInterface.dismiss()
//                    return@setOnKeyListener false
//                } else {
//                    return@setOnKeyListener false
//                }
//            }
//            return@setOnKeyListener false
//        }
        return dialog
    }
}
