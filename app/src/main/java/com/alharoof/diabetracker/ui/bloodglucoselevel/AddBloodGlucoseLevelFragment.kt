package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit.MILLIGRAMS_PER_DECILITRE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.AFTER_BREAKFAST
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.AFTER_DINNER
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.AFTER_EXERCISE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.AFTER_LUNCH
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BEFORE_BREAKFAST
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BEFORE_DINNER
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BEFORE_EXERCISE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BEFORE_LUNCH
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BEFORE_SLEEP
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.BREAKFAST
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.DINNER
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.EXERCISE
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.FASTING
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.LUNCH
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.OTHER
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category.SNACK
import com.alharoof.diabetracker.util.CustomDividerItemDecoration
import com.alharoof.diabetracker.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.flBgl
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.rvCategories
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.sliderBgl
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvBloodGlucoseLevel
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvDate
import kotlinx.android.synthetic.main.add_blood_glucose_level_fragment.tvTime
import org.threeten.bp.ZoneId
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
    private val currentDateTime: ZonedDateTime = ZonedDateTime.now()
    private var selectedDateTime: ZonedDateTime = currentDateTime

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    private val onDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, month, day -> updateSelectedDate(year, month, day) }

    private val onTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hour, minute -> updateSelectedTime(hour, minute) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.add_blood_glucose_level_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddBloodGlucoseLevelViewModel::class.java)

        createDateTimeDialogs()
        setListeners()
        setInitialValues()
        setObservers()
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
                        MILLIGRAMS_PER_DECILITRE, selectedDateTime, DINNER
                    )
                )
            }
            else -> {
            }
        }
        return true
    }

    private fun updateSelectedTime(hour: Int, minute: Int) {
        selectedDateTime = ZonedDateTime.of(
            selectedDateTime.year, selectedDateTime.monthValue, selectedDateTime.dayOfMonth,
            hour, minute, selectedDateTime.second, selectedDateTime.nano, ZoneId.systemDefault()
        )
        tvTime.text = String.format("%02d:%02d", selectedDateTime.hour, selectedDateTime.minute)
    }

    private fun updateSelectedDate(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        selectedDateTime = ZonedDateTime.of(
            selectedYear, selectedMonth, selectedDay, selectedDateTime.hour, selectedDateTime.minute,
            selectedDateTime.second, selectedDateTime.nano, ZoneId.systemDefault()
        )
        tvDate.text =
            String.format("%02d %s, %04d", selectedDateTime.dayOfMonth, selectedDateTime.month, selectedDateTime.year)
    }

    private fun createDateTimeDialogs() {
        context?.let { ctx ->
            datePickerDialog = DatePickerDialog(
                ctx, onDateSetListener, currentDateTime.year, currentDateTime.monthValue, currentDateTime.dayOfMonth
            )
            timePickerDialog = TimePickerDialog(
                ctx, onTimeSetListener, currentDateTime.hour, currentDateTime.minute, true
            )
        }
    }

    private fun setListeners() {
        sliderBgl.positionListener = { pos ->
            val selectedSliderValue = (MIN_BGL + MAX_BGL * pos).toInt()
            sliderBgl.bubbleText = "$selectedSliderValue"
            tvBloodGlucoseLevel.text = "$selectedSliderValue"

            setStartEndSliderTextVisibility(selectedSliderValue)
            setBGLColor(selectedSliderValue)
        }

        tvDate.setOnClickListener { datePickerDialog?.show() }
        tvTime.setOnClickListener { timePickerDialog?.show() }
    }

    private fun setInitialValues() {
        tvDate.text =
            String.format("%02d %s, %04d", currentDateTime.dayOfMonth, currentDateTime.month, currentDateTime.year)
        tvTime.text = String.format("%02d:%02d", currentDateTime.hour, currentDateTime.minute)
        tvBloodGlucoseLevel.text = "100"
        sliderBgl.position = 0.2f
        sliderBgl.bubbleText = "${100}"

        rvCategories.addItemDecoration(CustomDividerItemDecoration(activity!!, CustomDividerItemDecoration.GRID))
        rvCategories.adapter = CategoriesAdapter(getCategories())
    }

    private fun setBGLColor(value: Int) {
        when (value) {
            in 0..69 -> flBgl.setBackgroundResource(R.color.bgl_high)
            in 70..79 -> flBgl.setBackgroundResource(R.color.bgl_warn)
            in 140..179 -> flBgl.setBackgroundResource(R.color.bgl_warn)
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

    private fun setObservers() {
        viewModel.insertStatus.observe(viewLifecycleOwner, Observer<Result<BloodGlucoseLevel>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    context?.showToast("Added Successfully")
                    setInitialValues()

                    activity?.let { act ->
                        act.supportFragmentManager.beginTransaction()
                            .replace(R.id.container, BloodGlucoseLevelLogFragment.newInstance())
                            .commitNow()
                    }
                }
                is Error -> {
                    context?.showToast("Failed!!!")
                }
            }
        })
    }

    private fun getCategories(): List<Category> {
        return listOf(
            OTHER, FASTING, SNACK,
            BEFORE_BREAKFAST, BREAKFAST, AFTER_BREAKFAST,
            BEFORE_LUNCH, LUNCH, AFTER_LUNCH,
            BEFORE_DINNER, DINNER, AFTER_DINNER,
            BEFORE_EXERCISE, EXERCISE, AFTER_EXERCISE, BEFORE_SLEEP
        )
    }
}
