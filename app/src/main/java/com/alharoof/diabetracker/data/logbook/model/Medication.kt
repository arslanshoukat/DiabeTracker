package com.alharoof.diabetracker.data.logbook.model

data class Medication(
    val name: String,
    val medCode: MedicationEnum,
    val dose: Int,
    val doseUnit: DoseUnit
) {

    companion object {
        const val OTHER = 0
        const val PILLS = 1
        const val RAPID_ACTING_INSULIN = 2
        const val SHORT_ACTING_INSULIN = 3
        const val INTERMEDIATE_ACTING_INSULIN = 4
        const val LONG_ACTING_INSULIN = 5
    }
}