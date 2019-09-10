package com.alharoof.diabetracker.util

import com.alharoof.diabetracker.data.logbook.model.Medication
import com.alharoof.diabetracker.data.logbook.model.MedicationEnum

fun getRapidActingInsulins(): List<MedicationEnum> {
    return MedicationEnum.values().filter { it.type == Medication.RAPID_ACTING_INSULIN }.toList()
}

fun getShortActingInsulins(): List<MedicationEnum> {
    return MedicationEnum.values().filter { it.type == Medication.SHORT_ACTING_INSULIN }.toList()
}

fun getBolusInsulins(): List<MedicationEnum> {
    return MedicationEnum.values()
        .filter { it.type == Medication.RAPID_ACTING_INSULIN || it.type == Medication.SHORT_ACTING_INSULIN }.toList()
}

fun getBasalInsulins(): List<MedicationEnum> {
    return MedicationEnum.values()
        .filter { it.type == Medication.INTERMEDIATE_ACTING_INSULIN || it.type == Medication.LONG_ACTING_INSULIN }
        .toList()
}

fun getIntermediateActingInsulins(): List<MedicationEnum> {
    return MedicationEnum.values().filter { it.type == Medication.INTERMEDIATE_ACTING_INSULIN }.toList()
}

fun getLongActingInsulins(): List<MedicationEnum> {
    return MedicationEnum.values().filter { it.type == Medication.LONG_ACTING_INSULIN }.toList()
}

fun getPills(): List<MedicationEnum> {
    return MedicationEnum.values().filter { it.type == Medication.PILLS }.toList()
}