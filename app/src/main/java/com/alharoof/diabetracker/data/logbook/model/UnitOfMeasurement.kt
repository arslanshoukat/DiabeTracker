package com.alharoof.diabetracker.data.logbook.model

enum class UnitOfMeasurement(val code: Int, val title: String, val desc: String) {
    METRIC(0, "Metric", "kg, gram, cm"), IMPERIAL(1, "Imperial", "lb, oz, inch")
}