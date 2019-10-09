package com.alharoof.diabetracker.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R

class CalculatorFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
    }
}
