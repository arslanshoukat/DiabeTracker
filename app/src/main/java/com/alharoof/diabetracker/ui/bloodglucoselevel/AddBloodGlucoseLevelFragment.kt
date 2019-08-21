package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.flBgl
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.sliderBgl
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvBloodGlucoseLevel
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvDate
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvTime
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddBloodGlucoseLevelFragment : DaggerFragment() {

    companion object {
        fun newInstance() = AddBloodGlucoseLevelFragment()

        private const val MIN_BGL = 0
        private const val MAX_BGL = 500
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AddBloodGlucoseLevelViewModel

    private var isSliderStartTextVisible: Boolean = true
    private var isSliderEndTextVisible: Boolean = true
    private lateinit var dt: ZonedDateTime

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.add_blood_glucose_level_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddBloodGlucoseLevelViewModel::class.java)

        setupObserver()
        setInitialValues()

        sliderBgl.positionListener = { pos ->
            val selectedSliderValue = (MIN_BGL + MAX_BGL * pos).toInt()
            sliderBgl.bubbleText = "$selectedSliderValue"
            tvBloodGlucoseLevel.text = "$selectedSliderValue"

            setStartEndSliderTextVisibility(selectedSliderValue)
            setBGLColor(selectedSliderValue)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add_blood_glucose_level_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_save -> {
                viewModel.addBloodGlucoseLevel(
                    BloodGlucoseLevel(
                        tvBloodGlucoseLevel.text.toString().toInt(),
                        MILLIGRAMS_PER_DECILITRE, dt, DINNER
                    )
                )
            }
            else -> {
            }
        }
        return true
    }

    private fun setInitialValues() {
        dt = ZonedDateTime.now()

        tvDate.text = "${dt.dayOfMonth} ${dt.month}, ${dt.year}"
        tvTime.text = "${dt.hour}:${dt.minute}"
        tvBloodGlucoseLevel.text = "100"
        sliderBgl.position = 0.2f
        sliderBgl.bubbleText = "${100}"
    }

    private fun setBGLColor(value: Int) {
        when (value) {
            in 0..70 -> flBgl.setBackgroundResource(R.color.bgl_low)
            in 180..999 -> flBgl.setBackgroundResource(R.color.bgl_high)
            else -> flBgl.setBackgroundResource(R.color.bgl_normal)
        }
    }

    private fun setStartEndSliderTextVisibility(value: Int) {
        if (value <= 50 && isSliderStartTextVisible) {
            sliderBgl.startText = null
            isSliderStartTextVisible = false
        } else if (value >= 450 && isSliderEndTextVisible) {
            sliderBgl.endText = null
            isSliderEndTextVisible = false
        } else if (value in 51..449) {
            if (!isSliderStartTextVisible) {
                sliderBgl.startText = "$MIN_BGL"
                isSliderStartTextVisible = true
            }
            if (!isSliderEndTextVisible) {
                sliderBgl.endText = "$MAX_BGL"
                isSliderEndTextVisible = true
            }
        }
    }

    private fun setupObserver() {
        viewModel.insertStatus.observe(viewLifecycleOwner, Observer<Resource<BloodGlucoseLevel>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    context?.showToast("Added Successfully")
                    setInitialValues()
                }
                is Error -> {
                    context?.showToast("Failed!!!")
                }
            }
        })
    }
}
