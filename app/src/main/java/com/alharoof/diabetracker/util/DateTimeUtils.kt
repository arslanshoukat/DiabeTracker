package com.alharoof.diabetracker.util

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Arslan Shoukat on Aug 26, 2019 3:09 PM.
 */

object DateTimeUtils {

    private val standardFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm:ss a")

    fun getFormattedDateTime(dateTime: ZonedDateTime): String {
        return dateTime.format(standardFormatter)
    }
}
