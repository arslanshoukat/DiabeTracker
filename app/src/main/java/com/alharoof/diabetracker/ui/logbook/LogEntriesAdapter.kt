package com.alharoof.diabetracker.ui.logbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.ui.logbook.LogEntriesAdapter.LogEntryViewHolder
import com.alharoof.diabetracker.util.DateTimeUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.log_entry_list_item.ivCategory
import kotlinx.android.synthetic.main.log_entry_list_item.llBasal
import kotlinx.android.synthetic.main.log_entry_list_item.llBgl
import kotlinx.android.synthetic.main.log_entry_list_item.llBolus
import kotlinx.android.synthetic.main.log_entry_list_item.llCarbs
import kotlinx.android.synthetic.main.log_entry_list_item.llCategory
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalDose
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalDoseUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalMedication
import kotlinx.android.synthetic.main.log_entry_list_item.tvBgl
import kotlinx.android.synthetic.main.log_entry_list_item.tvBglUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusDose
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusDoseUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusMedication
import kotlinx.android.synthetic.main.log_entry_list_item.tvCarbs
import kotlinx.android.synthetic.main.log_entry_list_item.tvCategory
import kotlinx.android.synthetic.main.log_entry_list_item.tvTime

class LogEntriesAdapter(
    private var logEntries: MutableList<LogEntry> = mutableListOf()
) : RecyclerView.Adapter<LogEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogEntryViewHolder {
        return LogEntryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.alharoof.diabetracker.R.layout.log_entry_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = logEntries.size

    override fun onBindViewHolder(viewHolder: LogEntryViewHolder, position: Int) {
        viewHolder.bindView(logEntries[position])
    }

    fun updateLogEntries(newLogEntries: List<LogEntry>) {
//        if (logEntries.isEmpty()) {
//            logEntries.addAll(newLogEntries)
//        } else {
        val logEntriesDiffCallback = LogEntryDiffCallback(logEntries, newLogEntries)
        val diffResult = DiffUtil.calculateDiff(logEntriesDiffCallback)

        logEntries.clear()
        logEntries.addAll(newLogEntries)
        diffResult.dispatchUpdatesTo(this)
//        }
    }

    class LogEntryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindView(logEntry: LogEntry) {
            tvTime.text = DateTimeUtils.getFormattedDateTime(logEntry.dateTime)
//                String.format(
//                "%02d %s, %04d %02d:%02d:%02d",
//                logEntry.dateTime.dayOfMonth, logEntry.dateTime.month.toString(), logEntry.dateTime.year,
//                logEntry.dateTime.hour, logEntry.dateTime.minute, logEntry.dateTime.second
//            )

            if (logEntry.bgl != null) {
                tvBgl.text = logEntry.bgl.toString()
                tvBglUnit.text = logEntry.bglUnit?.symbol ?: ""

                setBglFieldsVisibility(true)
            } else setBglFieldsVisibility(false)

            logEntry.basalMedication?.let { basal ->
                tvBasalMedication.text = basal.name
                tvBasalDose.text = basal.dose.toString()
                tvBasalDoseUnit.text = basal.doseUnit.symbol

                setBasalMedFieldsVisibility(true)
            } ?: setBasalMedFieldsVisibility(false)

            logEntry.bolusMedication?.let { bolus ->
                tvBolusMedication.text = bolus.name
                tvBolusDose.text = bolus.dose.toString()
                tvBolusDoseUnit.text = bolus.doseUnit.symbol

                setBolusMedFieldsVisibility(true)
            } ?: setBolusMedFieldsVisibility(false)

            logEntry.carbs?.let { carbs ->
                tvCarbs.text = carbs.toString()
                setCarbsFieldsVisibility(true)
            } ?: setCarbsFieldsVisibility(false)

            logEntry.category?.let { category ->
                ivCategory.setImageResource(category.icon)
                tvCategory.text = category.title

                setCategoryFieldsVisibility(true)
            } ?: setCategoryFieldsVisibility(false)
        }

        private fun setBglFieldsVisibility(visible: Boolean) {
            llBgl.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setBasalMedFieldsVisibility(visible: Boolean) {
            llBasal.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setBolusMedFieldsVisibility(visible: Boolean) {
            llBolus.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setCarbsFieldsVisibility(visible: Boolean) {
            llCarbs.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setCategoryFieldsVisibility(visible: Boolean) {
            llCategory.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }

    class LogEntryDiffCallback(
        private val oldLogEntries: List<LogEntry>,
        private val newLogEntries: List<LogEntry>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldLogEntries.size

        override fun getNewListSize(): Int = newLogEntries.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLogEntries[oldItemPosition].id == newLogEntries[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLogEntries[oldItemPosition] == newLogEntries[newItemPosition]
        }
    }
}
