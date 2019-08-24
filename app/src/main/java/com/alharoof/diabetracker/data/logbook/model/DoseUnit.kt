package com.alharoof.diabetracker.data.logbook.model

enum class DoseUnit(val code: Int, val symbol: String) {
    INSULIN_UNIT(0, "U"),
    MG(1, "mg")
}