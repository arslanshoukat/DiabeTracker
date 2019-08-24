package com.alharoof.diabetracker.util

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.model.BGLUnit.MILLIGRAMS_PER_DECILITRE
import com.alharoof.diabetracker.data.logbook.model.BGLUnit.MILLIMOLES_PER_LITRE
import com.alharoof.diabetracker.data.logbook.model.Category
import org.threeten.bp.ZonedDateTime

/**
 * Created by ashoukat on Aug 20, 2019 12:23 PM.
 */

object TestData {

    val logEntry = LogEntry(
        bgl = 100, unit = MILLIMOLES_PER_LITRE,
        dateTime = ZonedDateTime.parse("2019-08-19T22:52:30.725+05:00"), category = Category.AFTER_DINNER
    )

    val logEntry2 = LogEntry(
        bgl = 200, unit = MILLIGRAMS_PER_DECILITRE,
        dateTime = ZonedDateTime.parse("2019-08-21T10:23:40.245+05:00"), category = Category.AFTER_BREAKFAST
    )

    val logEntryList = listOf(logEntry, logEntry2)
}
