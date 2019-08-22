package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.blood_glucose_level_log_fragment.tvData
import javax.inject.Inject

class BloodGlucoseLevelLogFragment : DaggerFragment() {

    companion object {
        fun newInstance() = BloodGlucoseLevelLogFragment()
        const val TAG = "BglLogFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: BloodGlucoseLevelLogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.blood_glucose_level_log_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BloodGlucoseLevelLogViewModel::class.java)

        setObservers()
    }

    private fun setObservers() {
        viewModel.bglList.observe(viewLifecycleOwner, Observer<Result<List<BloodGlucoseLevel>>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    val successMsg = it.data?.toString() ?: "Success"
                    Log.d(TAG, successMsg)
                    tvData.text = successMsg
                }
                is Error -> {
                    Log.d(TAG, it.message ?: "Error")
                }
            }
        })
    }
}
