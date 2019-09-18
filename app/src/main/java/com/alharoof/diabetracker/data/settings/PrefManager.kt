package com.alharoof.diabetracker.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.alharoof.diabetracker.util.Constants

class PrefManager(context: Context) {
    companion object {
        const val PREFS_NAME = "diabetracker"

        const val PREF_LOW_BGL = "lowBgl"
        const val PREF_HYPO_BGL = "hypoBgl"
        const val PREF_TARGET_BGL = "targetBgl"
        const val PREF_HIGH_BGL = "highBgl"
        const val PREF_HYPER_BGL = "hyperBgl"
        const val PREF_INSULIN_SENSITIVITY_FACTOR = "insulinSensitivityFactor"
        const val PREF_INSULIN_TO_CARB_RATIO = "insulinToCarbRatio"

        const val PREF_BASAL_INSULIN_CODE = "basalInsulinCode"
        const val PREF_BOLUS_INSULIN_CODE = "bolusInsulinCode"

        const val PREF_UNIT_OF_MEASUREMENT_CODE = "unitOfMeasurementCode"
        const val PREF_BGL_UNIT_CODE = "bglUnitCode"

        const val PREF_FIRST_NAME = "firstName"
        const val PREF_LAST_NAME = "lastName"
        const val PREF_WEIGHT = "weight"
        const val PREF_HEIGHT = "height"
    }

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setLowBgl(lowBgl: Int) {
        prefs.edit().putInt(PREF_LOW_BGL, lowBgl).apply()
    }

    fun getLowBgl(): Int {
        return prefs.getInt(PREF_LOW_BGL, Constants.DEFAULT_LOW_BGL)
    }

    fun setHypoBgl(HypoBgl: Int) {
        prefs.edit().putInt(PREF_HYPO_BGL, HypoBgl).apply()
    }

    fun getHypoBgl(): Int {
        return prefs.getInt(PREF_HYPO_BGL, Constants.DEFAULT_HYPO_BGL)
    }

    fun setTargetBgl(TargetBgl: Int) {
        prefs.edit().putInt(PREF_TARGET_BGL, TargetBgl).apply()
    }

    fun getTargetBgl(): Int {
        return prefs.getInt(PREF_TARGET_BGL, Constants.DEFAULT_TARGET_BGL)
    }

    fun setHighBgl(HighBgl: Int) {
        prefs.edit().putInt(PREF_HIGH_BGL, HighBgl).apply()
    }

    fun getHighBgl(): Int {
        return prefs.getInt(PREF_HIGH_BGL, Constants.DEFAULT_HIGH_BGL)
    }

    fun setHyperBgl(HyperBgl: Int) {
        prefs.edit().putInt(PREF_HYPER_BGL, HyperBgl).apply()
    }

    fun getHyperBgl(): Int {
        return prefs.getInt(PREF_HYPER_BGL, Constants.DEFAULT_HYPER_BGL)
    }

    fun setInsulinSensitivityFactor(isf: Float) {
        prefs.edit().putFloat(PREF_INSULIN_SENSITIVITY_FACTOR, isf).apply()
    }

    fun getInsulinSensitivityFactor(): Float {
        return prefs.getFloat(PREF_INSULIN_SENSITIVITY_FACTOR, Constants.DEFAULT_INSULIN_SENSITIVITY_FACTOR)
    }

    fun setInsulinToCarbRatio(icr: Float) {
        prefs.edit().putFloat(PREF_INSULIN_TO_CARB_RATIO, icr).apply()
    }

    fun getInsulinToCarbRatio(): Float {
        return prefs.getFloat(PREF_INSULIN_TO_CARB_RATIO, Constants.DEFAULT_INSULIN_TO_CARB_RATIO)
    }

    fun setBasalInsulin(basalInsulinCode: Int) {
        prefs.edit().putInt(PREF_BASAL_INSULIN_CODE, basalInsulinCode).apply()
    }

    fun getBasalInsulin(): Int {
        return prefs.getInt(PREF_BASAL_INSULIN_CODE, Constants.INVALID_INT)
    }

    fun setBolusInsulin(bolusInsulinCode: Int) {
        prefs.edit().putInt(PREF_BOLUS_INSULIN_CODE, bolusInsulinCode).apply()
    }

    fun getBolusInsulin(): Int {
        return prefs.getInt(PREF_BOLUS_INSULIN_CODE, Constants.INVALID_INT)
    }

    fun setUnitOfMeasurement(unitOfMeasurement: Int) {
        prefs.edit().putInt(PREF_UNIT_OF_MEASUREMENT_CODE, unitOfMeasurement).apply()
        //  todo: convert height, weight and other related attributes to selected unit
    }

    fun getUnitOfMeasurement(): Int {
        return prefs.getInt(PREF_UNIT_OF_MEASUREMENT_CODE, Constants.INVALID_INT)
    }

    fun setBglUnit(bglUnit: Int) {
        prefs.edit().putInt(PREF_BGL_UNIT_CODE, bglUnit).apply()
        //  todo: convert bgl to selected unit
    }

    fun getBglUnit(): Int {
        return prefs.getInt(PREF_BGL_UNIT_CODE, Constants.INVALID_INT)
    }

    fun setFirstName(firstName: String) {
        prefs.edit().putString(PREF_FIRST_NAME, firstName).apply()
    }

    fun getFirstName(): String? {
        return prefs.getString(PREF_FIRST_NAME, null)
    }

    fun setLastName(lastName: String) {
        prefs.edit().putString(PREF_LAST_NAME, lastName).apply()
    }

    fun getLastName(): String? {
        return prefs.getString(PREF_LAST_NAME, null)
    }

    fun setHeight(height: Float) {
        prefs.edit().putFloat(PREF_HEIGHT, height).apply()
    }

    fun getHeight(): Float {
        return prefs.getFloat(PREF_HEIGHT, Constants.INVALID_FLOAT)
    }

    fun setWeight(weight: Float) {
        prefs.edit().putFloat(PREF_WEIGHT, weight).apply()
    }

    fun getWeight(): Float {
        return prefs.getFloat(PREF_WEIGHT, Constants.INVALID_FLOAT)
    }
}