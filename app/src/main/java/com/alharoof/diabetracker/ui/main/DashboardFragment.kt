package com.alharoof.diabetracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.base.BaseFragment
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import kotlinx.android.synthetic.main.dashboard_fragment.tvGreeting
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastBglUnit
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastBglValue
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastCarbIntakeValue
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastMedicationTitle
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastMedicationValue
import kotlinx.android.synthetic.main.dashboard_fragment_start.clDashboard
import kotlinx.android.synthetic.main.dashboard_fragment_start.tvRecentLbl
import javax.inject.Inject

class DashboardFragment : BaseFragment(TAG) {

    companion object {
        private const val TAG = "DashboardFragment"

        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        tvGreeting.text = "Hello ${viewModel.getFirstName() ?: "There"}!"
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        tvRecentLbl.setOnClickListener {
            if (isFullView)
                animateCards(R.layout.dashboard_fragment_start)
            else
                animateCards(R.layout.dashboard_fragment)
        }
    }

    private var isFullView = false

    private fun animateCards(layoutId: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(activity, layoutId)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1000

        TransitionManager.beginDelayedTransition(clDashboard, transition)
        constraintSet.applyTo(clDashboard)

        isFullView = !isFullView
    }

    private fun setObservers() {
        viewModel.weeklyLogEntries.observe(viewLifecycleOwner, Observer<List<LogEntry>> { weeklyList ->
            weeklyList.filter { it.bgl == null }.map { it.bgl }
        })

        viewModel.lastBglLogEntry.observe(viewLifecycleOwner, Observer<LogEntry> {
            tvLastBglValue.text = "${it.bgl ?: "-"}"
            //  set unit equal to that of last bgl or user preference
            tvLastBglUnit.text = it.bglUnit?.symbol ?: viewModel.getBglUnitPreference()
        })

        viewModel.lastMedicationLogEntry.observe(viewLifecycleOwner, Observer<LogEntry> {
            tvLastMedicationValue.text = "${it.bolusMedication?.dose ?: "-"}"
            tvLastMedicationTitle.text = it.bolusMedication?.name ?: ""
        })

        viewModel.lastCarbIntakeLogEntry.observe(viewLifecycleOwner, Observer<LogEntry> {
            tvLastCarbIntakeValue.text = "${it.carbs ?: "-"}"
        })
    }
}
