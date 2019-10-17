package com.alharoof.diabetracker.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.Constants

class SettingsFragment : PreferenceFragmentCompat() {
    companion object {
        const val TAG = "SettingsFragment"

        fun newInstance() = SettingsFragment()
    }

//    private lateinit var viewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)

        setDefaultValues()
    }

    private fun setDefaultValues() {
        val unitOfMeasurementPref =
            findPreference<ListPreference>(getString(R.string.pref_unit_of_measurement_code_key))
        //  if no value is set by user
        if (unitOfMeasurementPref != null && unitOfMeasurementPref.value == null) {
            //  set unit of measurement default value to first item in list
            unitOfMeasurementPref.setValueIndex(0)
        }

        val bglUnitPref = findPreference<ListPreference>(getString(R.string.pref_bgl_unit_code_key))
        //  if no value is set by user
        if (bglUnitPref != null && bglUnitPref.value == null) {
            //  set bgl unit default value to first item in list
            bglUnitPref.setValueIndex(0)
        }

        val basalInsulinPref = findPreference<ListPreference>(getString(R.string.pref_basal_insulin_code_key))
        //  if no value is set by user
        if (basalInsulinPref != null && basalInsulinPref.value == null) {
            //  set basal insulin default value to first item in list
            basalInsulinPref.setValueIndex(0)
        }

        val bolusInsulinPref = findPreference<ListPreference>(getString(R.string.pref_bolus_insulin_code_key))
        //  if no value is set by user
        if (bolusInsulinPref != null && bolusInsulinPref.value == null) {
            //  set bolus insulin default value to first item in list
            bolusInsulinPref.setValueIndex(0)
        }

        val isfPref = findPreference<EditTextPreference>(getString(R.string.pref_insulin_sensitivity_factor_key))
        Log.d(TAG, "${isfPref?.layoutResource}")
        //  if no value is set by user
        if (isfPref != null && isfPref.text.isNullOrEmpty()) {
            //  set insulin sensitivity factor default value
            isfPref.text = "${Constants.DEFAULT_INSULIN_SENSITIVITY_FACTOR}"
        }

        val icrPref = findPreference<EditTextPreference>(getString(R.string.pref_insulin_to_carb_ratio_key))
        //  if no value is set by user
        if (icrPref != null && icrPref.text.isNullOrEmpty()) {
            //  set insulin to carb ratio default value
            icrPref.text = "${Constants.DEFAULT_INSULIN_TO_CARB_RATIO}"
        }

        val targetBglPref = findPreference<EditTextPreference>(getString(R.string.pref_target_bgl_key))
        if (targetBglPref != null && targetBglPref.text.isNullOrEmpty()) {
            targetBglPref.text = "${Constants.DEFAULT_TARGET_BGL}"
        }

        val lowBglPref = findPreference<EditTextPreference>(getString(R.string.pref_low_bgl_key))
        if (lowBglPref != null && lowBglPref.text.isNullOrEmpty()) {
            lowBglPref.text = "${Constants.DEFAULT_LOW_BGL}"
        }

        val hypoBglPref = findPreference<EditTextPreference>(getString(R.string.pref_hypo_bgl_key))
        if (hypoBglPref != null && hypoBglPref.text.isNullOrEmpty()) {
            hypoBglPref.text = "${Constants.DEFAULT_HYPO_BGL}"
        }

        val highBglPref = findPreference<EditTextPreference>(getString(R.string.pref_high_bgl_key))
        if (highBglPref != null && highBglPref.text.isNullOrEmpty()) {
            highBglPref.text = "${Constants.DEFAULT_HIGH_BGL}"
        }

        val hyperBglPref = findPreference<EditTextPreference>(getString(R.string.pref_hyper_bgl_key))
        if (hyperBglPref != null && hyperBglPref.text.isNullOrEmpty()) {
            hyperBglPref.text = "${Constants.DEFAULT_HYPER_BGL}"
        }
    }

    //  todo: add validation checks for settings with user input

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.settings_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
//    }
}
