package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.base.Resource
import com.alharoof.diabetracker.data.base.Resource.Loading
import com.alharoof.diabetracker.data.base.Resource.Success
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit.MILLIGRAMS_PER_DECILITRE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.DINNER
import com.alharoof.diabetracker.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.btnAddBgl
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.etBgl
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddBloodGlucoseLevelFragment : DaggerFragment() {

    companion object {
        fun newInstance() = AddBloodGlucoseLevelFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AddBloodGlucoseLevelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_blood_glucose_level_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddBloodGlucoseLevelViewModel::class.java)
        setupObserver()

        btnAddBgl.setOnClickListener {
            viewModel.addBloodGlucoseLevel(
                BloodGlucoseLevel(
                    etBgl.text.toString().toFloat(),
                    MILLIGRAMS_PER_DECILITRE,
                    ZonedDateTime.now(),
                    DINNER
                )
            )
        }
    }

    private fun setupObserver() {
        viewModel.insertStatus.observe(viewLifecycleOwner, Observer<Resource<BloodGlucoseLevel>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    context?.showToast("Added Successfully")
                }
                is Error -> {
                    context?.showToast("Failed!!!")
                }
            }
        })
    }
}
