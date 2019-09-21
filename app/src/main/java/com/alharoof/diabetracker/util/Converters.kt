package com.alharoof.diabetracker.util

import androidx.room.TypeConverter
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.DoseUnit
import com.alharoof.diabetracker.data.logbook.model.MedicationEnum
import org.threeten.bp.ZonedDateTime

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromStringToZonedDateTime(timestamp: String?): ZonedDateTime? {
        return if (timestamp.isNullOrEmpty()) null else ZonedDateTime.parse(timestamp)
    }

    @TypeConverter
    @JvmStatic
    fun fromZonedDateTimeToString(zonedDateTime: ZonedDateTime?): String? {
        return zonedDateTime?.toString()
    }

    @TypeConverter
    @JvmStatic
    fun fromCategoryEnumToCode(category: Category?): Int? {
        return category?.code
    }

    @TypeConverter
    @JvmStatic
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
    @JvmStatic
    fun fromBglUnitEnumToCode(bglUnit: BglUnit?): Int? {
        return bglUnit?.code
    }

    @TypeConverter
    @JvmStatic
    fun fromBglUnitCodeToEnum(bglUnitCode: Int?): BglUnit? {
        return when (bglUnitCode) {
            0 -> BglUnit.MILLIMOLES_PER_LITRE
            1 -> BglUnit.MILLIGRAMS_PER_DECILITRE
            else -> null
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromDoseUnitEnumToCode(doseUnit: DoseUnit?): Int? = doseUnit?.code

    @TypeConverter
    @JvmStatic
    fun fromDoseUnitCodeToEnum(doseUnitCode: Int?): DoseUnit? {
        return when (doseUnitCode) {
            0 -> DoseUnit.INSULIN_UNIT
            1 -> DoseUnit.MG
            else -> null
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromMedicationEnumToCode(medicationEnum: MedicationEnum?): Int? = medicationEnum?.code

    @TypeConverter
    @JvmStatic
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

    fun getMedicationFromProductName(prodName: String?): MedicationEnum? {
        return when (prodName) {
            MedicationEnum.AFREZZA.productName -> MedicationEnum.AFREZZA
            MedicationEnum.APIDRA.productName -> MedicationEnum.APIDRA
            MedicationEnum.BASAGLAR.productName -> MedicationEnum.BASAGLAR
            MedicationEnum.HUMALOG.productName -> MedicationEnum.HUMALOG
            MedicationEnum.HUMALOG_MIX_50_50.productName -> MedicationEnum.HUMALOG_MIX_50_50
            MedicationEnum.HUMALOG_MIX_75_25.productName -> MedicationEnum.HUMALOG_MIX_75_25
            MedicationEnum.HUMULIN_R.productName -> MedicationEnum.HUMULIN_R
            MedicationEnum.HUMULIN_N.productName -> MedicationEnum.HUMULIN_N
            MedicationEnum.HUMULIN_50_50.productName -> MedicationEnum.HUMULIN_50_50
            MedicationEnum.HUMULIN_70_30.productName -> MedicationEnum.HUMULIN_70_30
            MedicationEnum.LANTUS.productName -> MedicationEnum.LANTUS
            MedicationEnum.LEVEMIR.productName -> MedicationEnum.LEVEMIR
            MedicationEnum.NOVOLIN.productName -> MedicationEnum.NOVOLIN
            MedicationEnum.NOVOLIN_70_30.productName -> MedicationEnum.NOVOLIN_70_30
            MedicationEnum.NOVOLOG.productName -> MedicationEnum.NOVOLOG
            MedicationEnum.NOVOLOG_MIX_70_30.productName -> MedicationEnum.NOVOLOG_MIX_70_30
            MedicationEnum.NOVORAPID.productName -> MedicationEnum.NOVORAPID
            MedicationEnum.NPH.productName -> MedicationEnum.NPH
            MedicationEnum.TOUJEO.productName -> MedicationEnum.TOUJEO
            MedicationEnum.TRESIBA.productName -> MedicationEnum.TRESIBA
            MedicationEnum.VELOSULIN.productName -> MedicationEnum.VELOSULIN
            else -> null
        }
    }
}