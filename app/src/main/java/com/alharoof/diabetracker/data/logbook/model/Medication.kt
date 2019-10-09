package com.alharoof.diabetracker.data.logbook.model

data class Medication(
    val medCode: MedicationEnum,
    val dose: Int,
    val doseUnit: DoseUnit,
    val name: String = medCode.productName
) {

    companion object {
        const val OTHER = 0
        const val DRUGS = 1
        const val RAPID_ACTING_INSULIN = 2
        const val SHORT_ACTING_INSULIN = 3
        const val INTERMEDIATE_ACTING_INSULIN = 4
        const val LONG_ACTING_INSULIN = 5
    }
}