package com.alharoof.diabetracker.data.bloodglucoselevel.model

enum class Category(val code: Int) {
    OTHER(0), FASTING(1), SNACK(2),
    BEFORE_BREAKFAST(3), BREAKFAST(4), AFTER_BREAKFAST(5),
    BEFORE_LUNCH(6), LUNCH(7), AFTER_LUNCH(8),
    BEFORE_DINNER(9), DINNER(10), AFTER_DINNER(11),
    BEFORE_EXERCISE(12), EXERCISE(13), AFTER_EXERCISE(14),
    BEFORE_SLEEP(15)
}