package com.alharoof.diabetracker.util

import androidx.room.TypeConverter
import com.alharoof.diabetracker.data.logbook.model.BGLUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.DoseUnit
import com.alharoof.diabetracker.data.logbook.model.MedicationEnum
import org.threeten.bp.ZonedDateTime

class Converters {
    @TypeConverter
    fun fromStringToZonedDateTime(timestamp: String?): ZonedDateTime? {
        return if (timestamp.isNullOrEmpty()) null else ZonedDateTime.parse(timestamp)
    }

    @TypeConverter
    fun fromZonedDateTimeToString(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.toString()
    }

    @TypeConverter
    fun fromCategoryEnumToCode(category: Category?): Int? {
        return category?.code
    }

    @TypeConverter
    fun fromCategoryCodeToEnum(categoryCode: Int?): Category? {
        return when (categoryCode) {
            0 -> Category.OTHER
            1 -> Category.FASTING
            2 -> Category.SNACK
            3 -> Category.BEFORE_BREAKFAST
            4 -> Category.BREAKFAST
            5 -> Category.AFTER_BREAKFAST
            6 -> Category.BEFORE_LUNCH
            7 -> Category.LUNCH
            8 -> Category.AFTER_LUNCH
            9 -> Category.BEFORE_DINNER
            10 -> Category.DINNER
            11 -> Category.AFTER_DINNER
            12 -> Category.BEFORE_EXERCISE
            13 -> Category.EXERCISE
            14 -> Category.AFTER_EXERCISE
            15 -> Category.BEFORE_SLEEP
            else -> null
        }
    }

    @TypeConverter
    fun fromBGLUnitEnumToCode(bglUnit: BGLUnit?): Int? {
        return bglUnit?.code
    }

    @TypeConverter
    fun fromBGLUnitCodeToEnum(bglUnitCode: Int?): BGLUnit? {
        return when (bglUnitCode) {
            0 -> BGLUnit.MILLIMOLES_PER_LITRE
            1 -> BGLUnit.MILLIGRAMS_PER_DECILITRE
            else -> null
        }
    }

    @TypeConverter
    fun fromDoseUnitEnumToCode(doseUnit: DoseUnit?): Int? = doseUnit?.code

    @TypeConverter
    fun fromDoseUnitCodeToEnum(doseUnitCode: Int?): DoseUnit? {
        return when (doseUnitCode) {
            0 -> DoseUnit.INSULIN_UNIT
            1 -> DoseUnit.MG
            else -> null
        }
    }

    @TypeConverter
    fun fromMedicationEnumToCode(medicationEnum: MedicationEnum?): Int? = medicationEnum?.code

    @TypeConverter
    fun fromMedicationCodeToEnum(medicationCode: Int?): MedicationEnum? {
        return when (medicationCode) {
            1 -> MedicationEnum.AFREZZA
            2 -> MedicationEnum.APIDRA
            3 -> MedicationEnum.BASAGLAR
            4 -> MedicationEnum.HUMALOG
            5 -> MedicationEnum.HUMALOG_MIX_50_50
            6 -> MedicationEnum.HUMALOG_MIX_75_25
            7 -> MedicationEnum.HUMULIN_R
            8 -> MedicationEnum.HUMULIN_N
            9 -> MedicationEnum.HUMULIN_50_50
            10 -> MedicationEnum.HUMULIN_70_30
            11 -> MedicationEnum.LANTUS
            12 -> MedicationEnum.LEVEMIR
            13 -> MedicationEnum.NOVOLIN
            14 -> MedicationEnum.NOVOLIN_70_30
            15 -> MedicationEnum.NOVOLOG
            16 -> MedicationEnum.NOVOLOG_MIX_70_30
            17 -> MedicationEnum.NOVORAPID
            18 -> MedicationEnum.NPH
            19 -> MedicationEnum.TOUJEO
            20 -> MedicationEnum.TRESIBA
            21 -> MedicationEnum.VELOSULIN
            else -> null
        }
    }
}