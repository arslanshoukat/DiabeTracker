package com.alharoof.diabetracker.data.logbook.model

import androidx.annotation.DrawableRes
import com.alharoof.diabetracker.R

enum class Category(val code: Int, val title: String, @DrawableRes val icon: Int) {

    OTHER(0, "Other", R.drawable.ic_not_applicable),
    FASTING(1, "Fasting", R.drawable.ic_fasting),
    SNACK(2, "Snack", R.drawable.ic_snack),
    BEFORE_BREAKFAST(3, "Before Breakfast", R.drawable.ic_breakfast),
    BREAKFAST(4, "Breakfast", R.drawable.ic_breakfast),
    AFTER_BREAKFAST(5, "After Breakfast", R.drawable.ic_breakfast),
    BEFORE_LUNCH(6, "Before Lunch", R.drawable.ic_lunch),
    LUNCH(7, "Lunch", R.drawable.ic_lunch),
    AFTER_LUNCH(8, "After Lunch", R.drawable.ic_lunch),
    BEFORE_DINNER(9, "Before Dinner", R.drawable.ic_dinner),
    DINNER(10, "Dinner", R.drawable.ic_dinner),
    AFTER_DINNER(11, "After Dinner", R.drawable.ic_dinner),
    BEFORE_EXERCISE(12, "Before Exercise", R.drawable.ic_exercise),
    EXERCISE(13, "Exercise", R.drawable.ic_exercise),
    AFTER_EXERCISE(14, "After Exercise", R.drawable.ic_exercise),
    BEFORE_SLEEP(15, "Before Sleep", R.drawable.ic_sleep)
}