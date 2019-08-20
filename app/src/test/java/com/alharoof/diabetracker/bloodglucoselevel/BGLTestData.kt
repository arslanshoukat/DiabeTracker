package com.alharoof.diabetracker.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit.MILLIMOLES_PER_LITRE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import org.threeten.bp.ZonedDateTime

/**
 * Created by ashoukat on Aug 20, 2019 12:23 PM.
 */

object TestData {

    val bloodGlucoseLevel = BloodGlucoseLevel(
        level = 100F, unit = MILLIMOLES_PER_LITRE,
        time = ZonedDateTime.parse("2019-08-19T22:52:30.725+05:00"), category = Category.BREAKFAST
    )
}
