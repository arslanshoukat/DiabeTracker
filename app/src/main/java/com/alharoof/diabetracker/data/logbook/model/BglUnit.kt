package com.alharoof.diabetracker.data.logbook.model

enum class BglUnit(val code: Int, val symbol: String) {
    MILLIGRAMS_PER_DECILITRE(1, "mg/dL"), MILLIMOLES_PER_LITRE(2, "mmol/L")
}
