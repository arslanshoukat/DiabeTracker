package com.alharoof.diabetracker.util

import androidx.room.TypeConverter
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.DoseUnit
import com.alharoof.diabetracker.data.logbook.model.MedicationEnum
import com.alharoof.diabetracker.data.logbook.model.UnitOfMeasurement
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object Converters {
    private val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? = value?.let { OffsetDateTime.parse(it, dateTimeFormatter) }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(dateTime: OffsetDateTime?): String? = dateTime?.format(dateTimeFormatter)

    @TypeConverter
    @JvmStatic
    fun fromCategoryEnumToCode(category: Category?): Int? {
        return category?.code
    }

    @TypeConverter
    @JvmStatic
    fun fromCategoryCodeToEnum(categoryCode: Int?): Category? {
        for (category in Category.values()) {
            if (category.code == categoryCode) {
                return category
            }
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun fromBglUnitEnumToCode(bglUnit: BglUnit?): Int? {
        return bglUnit?.code
    }

    @TypeConverter
    @JvmStatic
    fun fromBglUnitCodeToEnum(bglUnitCode: Int?): BglUnit? {
        for (bglUnit in BglUnit.values()) {
            if (bglUnit.code == bglUnitCode) {
                return bglUnit
            }
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun fromDoseUnitEnumToCode(doseUnit: DoseUnit?): Int? = doseUnit?.code

    @TypeConverter
    @JvmStatic
    fun fromDoseUnitCodeToEnum(doseUnitCode: Int?): DoseUnit? {
        for (doseUnit in DoseUnit.values()) {
            if (doseUnit.code == doseUnitCode) {
                return doseUnit
            }
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun fromMedicationEnumToCode(medicationEnum: MedicationEnum?): Int? = medicationEnum?.code

    //  todo: create medication hashmap for faster search / lookup (22/09/2019)
    @TypeConverter
    @JvmStatic
    fun fromMedicationCodeToEnum(medicationCode: Int?): MedicationEnum? {
        for (medication in MedicationEnum.values()) {
            if (medication.code == medicationCode) {
                return medication
            }
        }
        return null
    }

    @JvmStatic
    fun getMedicationFromProductName(prodName: String?): MedicationEnum? {
        for (medication in MedicationEnum.values()) {
            if (medication.productName == prodName) {
                return medication
            }
        }
        return null
    }

    @JvmStatic
    fun fromUnitOfMeasurementCodeToEnum(unitOfMeasurementCode: Int?): UnitOfMeasurement? {
        for (unitOfMeasurement in UnitOfMeasurement.values()) {
            if (unitOfMeasurement.code == unitOfMeasurementCode) {
                return unitOfMeasurement
            }
        }
        return null
    }

    @JvmStatic
    fun getUnitOfMeasurementFromTitle(title: String?): UnitOfMeasurement? {
        for (unitOfMeasurement in UnitOfMeasurement.values()) {
            if ("${unitOfMeasurement.title} (${unitOfMeasurement.desc})" == title) {
                return unitOfMeasurement
            }
        }
        return null
    }

    @JvmStatic
    fun getBglUnitFromTitle(title: String?): BglUnit? {
        for (bglUnit in BglUnit.values()) {
            if ("${bglUnit.title} (${bglUnit.symbol})" == title) {
                return bglUnit
            }
        }
        return null
    }
}