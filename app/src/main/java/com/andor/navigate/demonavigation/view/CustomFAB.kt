package com.andor.navigate.demonavigation.view

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class CustomFAB : FloatingActionButton, View.OnTouchListener {

    private var downRawX: Float = 0.toFloat()
    private var downRawY: Float = 0.toFloat()
    private var dX: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

        val action = motionEvent.action
        if (action == MotionEvent.ACTION_DOWN) {

            downRawX = motionEvent.rawX
            downRawY = motionEvent.rawY
            dX = view.x - downRawX
            dY = view.y - downRawY

            return true // Consumed

        } else if (action == MotionEvent.ACTION_MOVE) {

            val viewWidth = view.width
            val viewHeight = view.height

            val viewParent = view.parent as View
            val parentWidth = viewParent.width
            val parentHeight = viewParent.height

            var newX = motionEvent.rawX + dX
            newX = Math.max(0f, newX) // Don't allow the FAB past the left hand side of the parent
            newX = Math.min(
                (parentWidth - viewWidth).toFloat(),
                newX
            ) // Don't allow the FAB past the right hand side of the parent

            var newY = motionEvent.rawY + dY
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

            return true // Consumed

        } else if (action == MotionEvent.ACTION_UP) {

            val upRawX = motionEvent.rawX
            val upRawY = motionEvent.rawY

            val upDX = upRawX - downRawX
            val upDY = upRawY - downRawY

            return if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) { // A click
                performClick()
            } else { // A drag
                true // Consumed
            }

        } else {
            return super.onTouchEvent(motionEvent)
        }

    }

    companion object {

        val CLICK_DRAG_TOLERANCE =
            10f // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.
    }

}