package com.andor.navigate.demonavigation.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.andor.navigate.demonavigation.R
import com.andor.navigate.demonavigation.view.CustomFAB
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    private var downRawX: Float = 0.toFloat()
    private var downRawY: Float = 0.toFloat()
    private var dX: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        val sharedPreferences = this.activity!!.getSharedPreferences("stored_position", Context.MODE_PRIVATE)
        floatingActionButton.post {
            val parent = floatingActionButton.parent as View
            val savedPositionX = sharedPreferences.getFloat("pos_x", 0f)
            val savedPositionY = sharedPreferences.getFloat("pos_y", 0f)
            animateViewWithbound(floatingActionButton, savedPositionX, savedPositionY)
        }

        floatingActionButton.setOnTouchListener(View.OnTouchListener { view, motionEvent ->

            val action = motionEvent.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {

                    downRawX = motionEvent.rawX
                    downRawY = motionEvent.rawY
                    dX = view!!.x - downRawX
                    dY = view.y - downRawY

                    return@OnTouchListener true // Consumed

                }
                MotionEvent.ACTION_MOVE -> {

                    var newX = motionEvent.rawX + dX
                    var newY = motionEvent.rawY + dY

                    animateViewWithbound(view, newX, newY)

                    return@OnTouchListener true // Consumed

                }
                MotionEvent.ACTION_UP -> {

                    val upRawX = motionEvent.rawX
                    val upRawY = motionEvent.rawY

                    val upDX = upRawX - downRawX
                    val upDY = upRawY - downRawY

                    val sharedPreferences =
                        this.activity!!.getSharedPreferences("stored_position", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    val parent = view.parent as View
                    editor.putFloat("pos_x", upRawX / parent.width).apply()
                    editor.putFloat("pos_y", upRawY / parent.height).apply()

                    return@OnTouchListener if (Math.abs(upDX) < CustomFAB.CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CustomFAB.CLICK_DRAG_TOLERANCE) { // A click
                        view.performClick()
                    } else { // A drag
                        true // Consumed
                    }

                }
                else -> return@OnTouchListener view.onTouchEvent(motionEvent)
            }
        })

        btn_firstFragment.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun animateViewWithbound(view: View, x: Float, y: Float) {
        val viewWidth = view.width
        val viewHeight = view.height

        val viewParent = view.parent as View
        val parentWidth = viewParent.width
        val parentHeight = viewParent.height

        var newX = x
        var newY = y


        newX = Math.max(0f, newX) // Don't allow the FAB past the left hand side of the parent
        newX = Math.min(
            (parentWidth - viewWidth).toFloat(),
            newX
        ) // Don't allow the FAB past the right hand side of the parent


        newY = Math.max(0f, newY) // Don't allow the FAB past the top of the parent
        newY = Math.min(
            (parentHeight - viewHeight).toFloat(),
            newY
        ) // Don't allow the FAB past the bottom of the parent

        view.animate()
            .x(newX)
            .y(newY)
            .setDuration(0)
            .start()

    }

}
