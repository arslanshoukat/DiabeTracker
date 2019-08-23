package com.alharoof.diabetracker.util

import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit.MILLIGRAMS_PER_DECILITRE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit.MILLIMOLES_PER_LITRE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import org.threeten.bp.ZonedDateTime

/**
 * Created by ashoukat on Aug 20, 2019 12:23 PM.
 */

object TestData {

    val bloodGlucoseLevel = BloodGlucoseLevel(
        level = 100, unit = MILLIMOLES_PER_LITRE,
        time = ZonedDateTime.parse("2019-08-19T22:52:30.725+05:00"), category = Category.AFTER_DINNER
    )

    val bloodGlucoseLevel1 = BloodGlucoseLevel(
        level = 200, unit = MILLIGRAMS_PER_DECILITRE,
        time = ZonedDateTime.parse("2019-08-21T10:23:40.245+05:00"), category = Category.AFTER_BREAKFAST
    )

    val bglList = listOf(
        bloodGlucoseLevel,
        bloodGlucoseLevel1
    )
}
