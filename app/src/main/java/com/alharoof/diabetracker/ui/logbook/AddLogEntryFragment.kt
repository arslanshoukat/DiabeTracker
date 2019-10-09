package com.alharoof.diabetracker.ui.logbook

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.DoseUnit
import com.alharoof.diabetracker.data.logbook.model.Medication
import com.alharoof.diabetracker.extensions.intTextOrNull
import com.alharoof.diabetracker.extensions.isTextNotZero
import com.alharoof.diabetracker.extensions.showToast
import com.alharoof.diabetracker.util.CustomDividerItemDecoration
import com.alharoof.diabetracker.util.getBasalInsulins
import com.alharoof.diabetracker.util.getBolusInsulins
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.add_log_entry_fragment.etBasalDose
import kotlinx.android.synthetic.main.add_log_entry_fragment.etBolusDose
import kotlinx.android.synthetic.main.add_log_entry_fragment.etCarbs
import kotlinx.android.synthetic.main.add_log_entry_fragment.flBgl
import kotlinx.android.synthetic.main.add_log_entry_fragment.rvCategories
import kotlinx.android.synthetic.main.add_log_entry_fragment.sliderBgl
import kotlinx.android.synthetic.main.add_log_entry_fragment.spBasalMedication
import kotlinx.android.synthetic.main.add_log_entry_fragment.spBolusMedication
import kotlinx.android.synthetic.main.add_log_entry_fragment.tvBgl
import kotlinx.android.synthetic.main.add_log_entry_fragment.tvDate
import kotlinx.android.synthetic.main.add_log_entry_fragment.tvTime
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class AddLogEntryFragment : DaggerFragment() {

    companion object {
        fun newInstance() = AddLogEntryFragment()

        const val TAG = "AddLogEntryFragment"

        private const val MIN_BGL = 0
        private const val MAX_BGL = 500

        private val basalInsulins = getBasalInsulins()
        private val bolusInsulins = getBolusInsulins()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AddLogEntryViewModel

    private var isSliderStartTextVisible: Boolean = true
    private var isSliderEndTextVisible: Boolean = true
    private val currentDateTime: OffsetDateTime = OffsetDateTime.now()
    private var selectedDateTime: OffsetDateTime = currentDateTime
    private var selectedCategory: Category? = null

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    private lateinit var ctx: Context

    private val onDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, month, day -> updateSelectedDate(year, month + 1, day) }

    private val onTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hour, minute -> updateSelectedTime(hour, minute) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.add_log_entry_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddLogEntryViewModel::class.java)

        activity?.let { act -> act.title = act.resources.getString(R.string.title_add_log_entry) }

        createDateTimeDialogs()
        setInitialValues()
        setListeners()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_log_entry_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_save -> {
                viewModel.addLogEntry(
                    LogEntry(
                        dateTime = selectedDateTime,
                        bgl = tvBgl.text.toString().toInt(),
                        bglUnit = BglUnit.MILLIGRAMS_PER_DECILITRE,
                        basalMedication = if (etBasalDose.isTextNotZero())
                            Medication(
                                medCode = basalInsulins[spBasalMedication.selectedItemPosition],
                                dose = etBasalDose.intTextOrNull() ?: 0,
                                doseUnit = DoseUnit.INSULIN_UNIT
                            ) else null,
                        bolusMedication = if (etBolusDose.isTextNotZero())
                            Medication(
                                medCode = bolusInsulins[spBolusMedication.selectedItemPosition],
                                dose = etBolusDose.intTextOrNull() ?: 0,
                                doseUnit = DoseUnit.INSULIN_UNIT
                            ) else null,
                        carbs = if (etCarbs.isTextNotZero()) etCarbs.intTextOrNull() else null,
                        category = selectedCategory
                    )
                )
            }
            else -> {
            }
        }
        return true
    }

    private fun updateSelectedTime(hour: Int, minute: Int) {
        selectedDateTime = OffsetDateTime.of(
            selectedDateTime.year, selectedDateTime.monthValue, selectedDateTime.dayOfMonth,
            hour, minute, selectedDateTime.second, selectedDateTime.nano, OffsetDateTime.now().offset
        )
        tvTime.text = String.format("%02d:%02d", selectedDateTime.hour, selectedDateTime.minute)
    }

    private fun updateSelectedDate(selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
        selectedDateTime = OffsetDateTime.of(
            selectedYear, selectedMonth, selectedDay, selectedDateTime.hour, selectedDateTime.minute,
            selectedDateTime.second, selectedDateTime.nano, OffsetDateTime.now().offset
        )
        tvDate.text =
            String.format("%02d %s, %04d", selectedDateTime.dayOfMonth, selectedDateTime.month, selectedDateTime.year)
    }

    private fun createDateTimeDialogs() {
        context?.let { ctx ->
            datePickerDialog = DatePickerDialog(
                ctx,
                onDateSetListener,
                currentDateTime.year,
                currentDateTime.monthValue - 1,
                currentDateTime.dayOfMonth
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
            tvBgl.text = "$selectedSliderValue"

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
        tvBgl.text = "100"

        sliderBgl.position = 0.2f
        sliderBgl.bubbleText = "${100}"

        val basalAdapter =
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, basalInsulins.map { it.productName })
        basalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBasalMedication.prompt = "Intermediate/Long Acting Insulin"
        spBasalMedication.adapter = basalAdapter

        val bolusAdapter =
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, bolusInsulins.map { it.productName })
        bolusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBolusMedication.prompt = "Rapid/Short Acting Insulin"
        spBolusMedication.adapter = bolusAdapter

        rvCategories.addItemDecoration(CustomDividerItemDecoration(ctx, CustomDividerItemDecoration.GRID))
        rvCategories.adapter = CategoriesAdapter(categories = Category.values().asList(),
            onListItemClickListener = object : OnListItemClickListener<Category> {
                override fun onItemClicked(category: Category) {
                    selectedCategory = category
                }
            })
    }

    private fun setBGLColor(value: Int) {
        when (value) {
            in 0..70 -> flBgl.setBackgroundResource(R.color.bgl_high)
            in 71..80 -> flBgl.setBackgroundResource(R.color.bgl_warn)
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
        viewModel.insertStatus.observe(viewLifecycleOwner, Observer<Result<LogEntry>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    context?.showToast("Added Successfully")
                    setInitialValues()

                    //  navigate to log book
                    findNavController().navigate(R.id.nav_logbook)

                    viewModel.resetStatus()
                }
                is Error -> {
                    context?.showToast("Failed to add new entry!!!")
                }
            }
        })
    }
}
