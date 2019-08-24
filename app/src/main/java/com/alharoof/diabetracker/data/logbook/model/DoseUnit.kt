package com.alharoof.diabetracker.data.logbook.model

enum class DoseUnit(val code: Int, val symbol: String) {
    INSULIN_UNIT(1, "U"),
    MG(2, "mg")
}