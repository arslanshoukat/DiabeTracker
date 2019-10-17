package com.alharoof.diabetracker.data.settings

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.UnitOfMeasurement
import com.alharoof.diabetracker.util.Constants
import com.alharoof.diabetracker.util.getBasalInsulins
import com.alharoof.diabetracker.util.getBolusInsulins

class PrefManager(val context: Context) {
    //  todo: make prefs observable

    private val prefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun setLowBgl(lowBgl: Int) {
        prefs.edit().putString(context.getString(R.string.pref_low_bgl_key), "$lowBgl").apply()
    }

    fun getLowBgl(): Int {
        //  return user set low bgl or default if not set
        return prefs.getString(
            context.getString(R.string.pref_low_bgl_key),
            "${Constants.DEFAULT_LOW_BGL}"
        )?.toIntOrNull() ?: Constants.DEFAULT_LOW_BGL
    }

    fun setHypoBgl(HypoBgl: Int) {
        prefs.edit().putString(context.getString(R.string.pref_hypo_bgl_key), "$HypoBgl").apply()
    }

    fun getHypoBgl(): Int {
        return prefs.getString(
            context.getString(R.string.pref_hypo_bgl_key),
            "${Constants.DEFAULT_HYPO_BGL}"
        )?.toIntOrNull() ?: Constants.DEFAULT_HYPO_BGL
    }

    fun setTargetBgl(TargetBgl: Int) {
        prefs.edit().putString(context.getString(R.string.pref_target_bgl_key), "$TargetBgl").apply()
    }

    fun getTargetBgl(): Int {
        return prefs.getString(
            context.getString(R.string.pref_target_bgl_key),
            "${Constants.DEFAULT_TARGET_BGL}"
        )?.toIntOrNull() ?: Constants.DEFAULT_TARGET_BGL
    }

    fun setHighBgl(HighBgl: Int) {
        prefs.edit().putString(context.getString(R.string.pref_high_bgl_key), "$HighBgl").apply()
    }

    fun getHighBgl(): Int {
        return prefs.getString(
            context.getString(R.string.pref_high_bgl_key),
            "${Constants.DEFAULT_HIGH_BGL}"
        )?.toIntOrNull() ?: Constants.DEFAULT_HIGH_BGL
    }

    fun setHyperBgl(HyperBgl: Int) {
        prefs.edit().putString(context.getString(R.string.pref_hyper_bgl_key), "$HyperBgl").apply()
    }

    fun getHyperBgl(): Int {
        return prefs.getString(
            context.getString(R.string.pref_hyper_bgl_key),
            "${Constants.DEFAULT_HYPER_BGL}"
        )?.toIntOrNull() ?: Constants.DEFAULT_HYPER_BGL
    }

    fun setInsulinSensitivityFactor(isf: Float) {
        prefs.edit().putString(context.getString(R.string.pref_insulin_sensitivity_factor_key), "$isf").apply()
    }

    fun getInsulinSensitivityFactor(): Float {
        return prefs.getString(
            context.getString(R.string.pref_insulin_sensitivity_factor_key),
            "${Constants.DEFAULT_INSULIN_SENSITIVITY_FACTOR}"
        )?.toFloatOrNull() ?: Constants.DEFAULT_INSULIN_SENSITIVITY_FACTOR
    }

    fun setInsulinToCarbRatio(icr: Float) {
        prefs.edit().putString(context.getString(R.string.pref_insulin_to_carb_ratio_key), "$icr").apply()
    }

    fun getInsulinToCarbRatio(): Float {
        return prefs.getString(
            context.getString(R.string.pref_insulin_to_carb_ratio_key),
            "${Constants.DEFAULT_INSULIN_TO_CARB_RATIO}"
        )?.toFloatOrNull() ?: Constants.DEFAULT_INSULIN_TO_CARB_RATIO
    }

    fun setBasalInsulin(basalInsulinCode: Int) {
        prefs.edit().putString(context.getString(R.string.pref_basal_insulin_code_key), "$basalInsulinCode").apply()
    }

    fun getBasalInsulin(): Int {
        //  return basal insulin set by user or first basal insulin if not set
        val defaultBasal = getBasalInsulins()[0].code
        return prefs.getString(
            context.getString(R.string.pref_basal_insulin_code_key),
            "$defaultBasal"
        )?.toIntOrNull() ?: defaultBasal
    }

    fun setBolusInsulin(bolusInsulinCode: Int) {
        prefs.edit().putString(context.getString(R.string.pref_bolus_insulin_code_key), "$bolusInsulinCode").apply()
    }

    fun getBolusInsulin(): Int {
        //  return bolus insulin set by user or first bolus insulin if not set
        val defaultBolus = getBolusInsulins()[0].code
        return prefs.getString(
            context.getString(R.string.pref_bolus_insulin_code_key),
            "$defaultBolus"
        )?.toIntOrNull() ?: defaultBolus
    }

    fun setUnitOfMeasurement(unitOfMeasurement: Int) {
        prefs.edit().putString(context.getString(R.string.pref_unit_of_measurement_code_key), "$unitOfMeasurement")
            .apply()
        //  todo: convert height, weight and other related attributes to selected unit
    }

    fun getUnitOfMeasurement(): Int {
        val defaultUnitOfMeasurement = UnitOfMeasurement.METRIC.code
        return prefs.getString(
            context.getString(R.string.pref_unit_of_measurement_code_key),
            "$defaultUnitOfMeasurement"
        )?.toIntOrNull() ?: defaultUnitOfMeasurement
    }

    fun setBglUnit(bglUnit: Int) {
        prefs.edit().putString(context.getString(R.string.pref_bgl_unit_code_key), "$bglUnit").apply()
        //  todo: convert bgl to selected unit
    }

    fun getBglUnit(): Int {
        val defaultBglUnit = BglUnit.MILLIGRAMS_PER_DECILITRE.code
        return prefs.getString(context.getString(R.string.pref_bgl_unit_code_key), "$defaultBglUnit")?.toIntOrNull()
            ?: defaultBglUnit
    }

    fun setFirstName(firstName: String) {
        prefs.edit().putString(context.getString(R.string.pref_first_name_key), firstName).apply()
    }

    fun getFirstName(): String? {
        return prefs.getString(context.getString(R.string.pref_first_name_key), null)
    }

    fun setLastName(lastName: String) {
        prefs.edit().putString(context.getString(R.string.pref_last_name_key), lastName).apply()
    }

    fun getLastName(): String? {
        return prefs.getString(context.getString(R.string.pref_last_name_key), null)
    }

    fun setHeight(height: Float) {
        prefs.edit().putString(context.getString(R.string.pref_height_key), "$height").apply()
    }

    fun getHeight(): Float {
        return prefs.getString(context.getString(R.string.pref_height_key), null)?.toFloatOrNull()
        //  return invalid float to indicate height not set yet
            ?: Constants.INVALID_FLOAT
    }

    fun setWeight(weight: Float) {
        prefs.edit().putString(context.getString(R.string.pref_weight_key), "$weight").apply()
    }

    fun getWeight(): Float {
        return prefs.getString(context.getString(R.string.pref_weight_key), null)?.toFloatOrNull()
        //  return invalid float to indicate weight not set yet
            ?: Constants.INVALID_FLOAT
    }
}