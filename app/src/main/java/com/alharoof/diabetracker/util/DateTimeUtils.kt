package com.alharoof.diabetracker.util

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Arslan Shoukat on Aug 26, 2019 3:09 PM.
 */

object DateTimeUtils {

    private val standardFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm:ss a")
    private val dateFormatterForDashboardChart = DateTimeFormatter.ofPattern("dd MMM")

    fun getFormattedDateTime(dateTime: OffsetDateTime): String {
        return dateTime.format(standardFormatter)
    }

    fun getDateForDashboardChart(dateTime: OffsetDateTime): String {
        return dateTime.format(dateFormatterForDashboardChart)
    }
}
