package com.alharoof.diabetracker.util

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Arslan Shoukat on Aug 26, 2019 3:09 PM.
 */

object DateTimeUtils {

    private val standardFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm:ss a")
    private val standardDateFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    private val dateFormatterForDashboardChart = DateTimeFormatter.ofPattern("dd MMM")
    private val dateFormatterForLogbook = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")
    private val timeFormatterForLogbook = DateTimeFormatter.ofPattern("hh:mm a")

    fun getFormattedDateTime(dateTime: OffsetDateTime): String {
        return dateTime.format(standardFormatter)
    }

    fun getDateForDashboardChart(dateTime: OffsetDateTime): String {
        return dateTime.format(dateFormatterForDashboardChart)
    }

    fun getFormattedDate(dateTime: OffsetDateTime): String {
        return dateTime.format(standardDateFormatter)
    }

    fun getDateForLogbook(dateTime: OffsetDateTime): String {
        return dateTime.format(dateFormatterForLogbook)
    }

    fun getTimeForLogbook(dateTime: OffsetDateTime): String {
        return dateTime.format(timeFormatterForLogbook)
    }
}
