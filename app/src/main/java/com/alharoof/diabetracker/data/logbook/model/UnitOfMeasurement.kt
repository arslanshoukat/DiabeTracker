package com.alharoof.diabetracker.data.logbook.model

enum class UnitOfMeasurement(val code: Int, val title: String, val desc: String) {
    IMPERIAL(1, "Imperial", "lb, oz, inch"), METRIC(2, "Metric", "kg, gram, cm")
}