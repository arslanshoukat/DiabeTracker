package com.alharoof.diabetracker.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.base.BaseFragment
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.util.DateTimeUtils
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineDataSet.Mode.CUBIC_BEZIER
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.dashboard_fragment.chartBgl
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastBglUnit
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastBglValue
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastCarbIntakeValue
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastMedicationTitle
import kotlinx.android.synthetic.main.dashboard_fragment.tvLastMedicationValue
import kotlinx.android.synthetic.main.dashboard_fragment.tvName
import kotlinx.android.synthetic.main.dashboard_fragment_start.clDashboard
import javax.inject.Inject

class DashboardFragment : BaseFragment(TAG) {

    companion object {
        private const val TAG = "DashboardFragment"
        private const val TRANSITION_DURATION = 800

        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var isViewShown = false
    private lateinit var ctx: Context

    private var chartTypefaceLight: Typeface = Typeface.DEFAULT
    private var colorOnSurface: Int = Color.BLACK
    private var axisLabelColor: Int = colorOnSurface
    private var axisGridColor: Int = colorOnSurface
    private var axisLineColor: Int = colorOnSurface
    private var lineColor: Int = Color.BLUE
    private var lineCircleColor: Int = lineColor
    private var lineFillColor: Int = lineCircleColor
    private var highlightColor: Int = lineColor

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dashboard_fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        tvName.text = " ${viewModel.getFirstName() ?: ""}!"
        initializeStyleResources()
        setupChart()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        Handler(Looper.getMainLooper()).post {
            animateCards(R.layout.dashboard_fragment)
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    private fun animateCards(layoutId: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(activity, layoutId)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = TRANSITION_DURATION.toLong()

        TransitionManager.beginDelayedTransition(clDashboard, transition)
        constraintSet.applyTo(clDashboard)

        isViewShown = !isViewShown
    }

    private fun initializeStyleResources() {
        colorOnSurface = activity?.resources?.getColor(R.color.color_on_surface) ?: Color.BLACK
        axisLabelColor = ColorUtils.setAlphaComponent(colorOnSurface, 128)
        axisGridColor = ColorUtils.setAlphaComponent(colorOnSurface, 64)
        axisLineColor = ColorUtils.setAlphaComponent(colorOnSurface, 128)
        lineColor = activity?.resources?.getColor(R.color.color_primary) ?: Color.BLUE
        lineCircleColor = lineColor
        lineFillColor = activity?.resources?.getColor(R.color.color_primary_light) ?: lineColor
        highlightColor =
            ColorUtils.setAlphaComponent(activity?.resources?.getColor(R.color.color_secondary) ?: lineColor, 196)
        chartTypefaceLight = ResourcesCompat.getFont(ctx, R.font.titillium_web_light) ?: Typeface.DEFAULT
    }

    /**
     * This method is used to setup and style various components (axis, legends, grids, gestures etc) of chart.
     */
    private fun setupChart() {
        // disable description text
        chartBgl.description.isEnabled = false
        chartBgl.legend.isEnabled = false
        chartBgl.setDrawGridBackground(false)
        // enable touch gestures
        chartBgl.setTouchEnabled(true)
        // enable scaling and dragging
        chartBgl.isDragEnabled = false
        chartBgl.setScaleEnabled(false)
        chartBgl.setPinchZoom(false)
        chartBgl.setNoDataText("You have not added any entry yet!")
        chartBgl.setNoDataTextTypeface(chartTypefaceLight)
        // set an alternative background color
//        chartBgl.setBackgroundColor(DKGRAY)

        val xAxis = chartBgl.xAxis
        xAxis.textColor = axisLabelColor
        xAxis.textSize = 9f
        xAxis.typeface = chartTypefaceLight
        xAxis.axisLineColor = axisLineColor
        xAxis.isGranularityEnabled = true
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.position = BOTTOM
        xAxis.isEnabled = true

        val rightAxis = chartBgl.axisRight
        rightAxis.labelCount = 4
        rightAxis.textColor = axisLabelColor
        rightAxis.textSize = 10f
        rightAxis.typeface = chartTypefaceLight
        rightAxis.axisLineColor = Color.TRANSPARENT
        rightAxis.gridColor = axisGridColor
        rightAxis.setDrawGridLines(true)
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        rightAxis.isEnabled = true

        val leftAxis = chartBgl.axisLeft
        leftAxis.isEnabled = false

        chartBgl.isAutoScaleMinMaxEnabled = true
    }

    private fun updateChartData(weeklyBglMap: HashMap<String, Int>) {
        val yValues = mutableListOf<Entry>()
        val xValues = mutableListOf<String>()
        var i = 0f
        for ((time, bgl) in weeklyBglMap) {
            //  create list of bgl values for yAxis
            yValues.add(Entry(i, bgl.toFloat()))
            xValues.add(time)
            i++
        }

        chartBgl.xAxis.valueFormatter = IndexAxisValueFormatter(xValues)

        val set1 = LineDataSet(yValues, "Bgl")
        set1.mode = CUBIC_BEZIER
        set1.cubicIntensity = 0.2f
        set1.setDrawFilled(true)
        set1.setDrawCircles(true)
        set1.lineWidth = 2f
        set1.circleRadius = 4f
        set1.setCircleColor(lineCircleColor)
        set1.highLightColor = highlightColor
        set1.color = lineColor
        set1.fillColor = lineFillColor
        set1.fillAlpha = 50
        set1.setDrawVerticalHighlightIndicator(true)
        set1.setDrawHorizontalHighlightIndicator(true)

        val dataSets = listOf<LineDataSet>(set1)

        val lineData = LineData(dataSets)
        lineData.setValueTypeface(chartTypefaceLight)
        lineData.setValueTextSize(9f)
        lineData.setDrawValues(false)
        lineData.isHighlightEnabled = true

        chartBgl.data = lineData

        chartBgl.animateY(TRANSITION_DURATION)
        // refresh the drawing
        chartBgl.invalidate()
    }

    private fun setObservers() {
        viewModel.weeklyLogEntries.observe(viewLifecycleOwner, Observer<List<LogEntry>> { data ->
            val weeklyBglList: List<Pair<String, Int>> =
                data.map { Pair(DateTimeUtils.getDateForDashboardChart(it.dateTime), it.bgl ?: 0) }.toList()
            val weeklyBglMap = LinkedHashMap<String, Int>()

            //  todo: change logic to get list of average bgl for last 7 days
            for (pair in weeklyBglList) {
                if (weeklyBglMap.contains(pair.first)) {
                    weeklyBglMap[pair.first] = (weeklyBglMap[pair.first] ?: 0) + pair.second / 2
                } else {
                    weeklyBglMap[pair.first] = pair.second
                }
            }

            Log.d(TAG, weeklyBglMap.toString())

            updateChartData(weeklyBglMap)
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
