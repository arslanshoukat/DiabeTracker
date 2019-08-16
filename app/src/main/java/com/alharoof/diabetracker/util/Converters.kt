package com.alharoof.diabetracker.util

import androidx.room.TypeConverter
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
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
}