package com.alharoof.diabetracker.data.logbook.model

enum class BglUnit(val code: Int, val symbol: String) {
    MILLIMOLES_PER_LITRE(0, "mmol/L"), MILLIGRAMS_PER_DECILITRE(1, "mg/dL")
}
