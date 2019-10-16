package com.alharoof.diabetracker.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.alharoof.diabetracker.R

/**
 * Created by Arslan Shoukat on Oct 17, 2019 1:18 AM.
 */

/**
 * This function is used to create background drawable with tint for various states for navigation menu items.
 */
fun navigationItemBackground(context: Context): Drawable? {
    // Need to inflate the drawable and CSL via AppCompatResources to work on Lollipop
    var background = AppCompatResources.getDrawable(context, R.drawable.navigation_item_background)
    if (background != null) {
        val tint = AppCompatResources.getColorStateList(context, R.color.navigation_item_background_tint)
        background = DrawableCompat.wrap(background.mutate())
        background.setTintList(tint)
    }
    return background
}