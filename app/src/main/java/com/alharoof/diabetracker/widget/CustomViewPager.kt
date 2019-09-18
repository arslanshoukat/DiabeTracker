package com.alharoof.diabetracker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by Arslan Shoukat on Sep 18, 2019 10:04 AM.
 */

class CustomViewPager : ViewPager {

    var swipeable: Boolean = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return swipeable && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return swipeable && super.onTouchEvent(event)
    }
}