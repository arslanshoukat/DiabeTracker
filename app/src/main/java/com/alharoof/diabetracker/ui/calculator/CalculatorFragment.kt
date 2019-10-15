package com.alharoof.diabetracker.ui.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.base.BaseFragment
import kotlinx.android.synthetic.main.calculator_fragment.etBgl
import kotlinx.android.synthetic.main.calculator_fragment.etCarbs
import kotlinx.android.synthetic.main.calculator_fragment.tvActiveInsulin
import kotlinx.android.synthetic.main.calculator_fragment.tvInstruction
import javax.inject.Inject
import kotlin.math.roundToInt

class CalculatorFragment : BaseFragment(TAG) {

    companion object {
        const val TAG = "CalculatorFragment"

        fun newInstance() = CalculatorFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CalculatorViewModel

    private var activeInsulin = 0
    private var bgl: Float? = null
    private var carbsIntake: Float? = null

    private var targetBgl: Float = 0F
    private var insulinToCarbRatio: Float = 0F
    private var insulinSensitivityFactor: Float = 0F

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculatorViewModel::class.java)

        targetBgl = viewModel.loadTargetBgl().toFloat()
        insulinToCarbRatio = viewModel.loadInsulinToCarbRatio()
        insulinSensitivityFactor = viewModel.loadInsulinSensitivityFactor()

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.activeInsulin.observe(viewLifecycleOwner, Observer {
            tvActiveInsulin.text = "$it"
            activeInsulin = it
        })
    }

    private fun setListeners() {
        etBgl.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bgl = s?.toString()?.toFloatOrNull()
                updateInstruction()
            }
        })
        etCarbs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                carbsIntake = s?.toString()?.toFloatOrNull()
                updateInstruction()
            }
        })
    }

    //  todo: improve calculator algo to provide smarter advice / instruction
    //   use active insulin to improve algo
    private fun updateInstruction() {
        if (bgl == null && carbsIntake == null) {
            tvInstruction.text = getString(R.string.calculator_instruction_need_input)
            return
        }

        val doseToCoverBgl: Float = bgl?.let { (it - targetBgl) / insulinSensitivityFactor } ?: 0f
        val doseToCoverCarbs: Float = carbsIntake?.let { it / insulinToCarbRatio } ?: 0f

        val totalDose: Float = doseToCoverCarbs + doseToCoverBgl

        tvInstruction.text = when {
            totalDose > 0 -> getString(R.string.calculator_instruction_dose_needed, totalDose.roundToInt())
            totalDose < 0 -> getString(
                R.string.calculator_instruction_carbs_needed,
                (totalDose * insulinToCarbRatio * -1).roundToInt()
            )
            else -> getString(R.string.calculator_instruction_no_dose_needed)
        }
    }
}
