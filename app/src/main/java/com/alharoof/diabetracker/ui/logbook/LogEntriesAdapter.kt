package com.alharoof.diabetracker.ui.logbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.util.DateTimeUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_header_item.tvHeader
import kotlinx.android.synthetic.main.log_entry_list_item.divBasal
import kotlinx.android.synthetic.main.log_entry_list_item.divBolus
import kotlinx.android.synthetic.main.log_entry_list_item.divCarbs
import kotlinx.android.synthetic.main.log_entry_list_item.ivCategory
import kotlinx.android.synthetic.main.log_entry_list_item.llBasal
import kotlinx.android.synthetic.main.log_entry_list_item.llBgl
import kotlinx.android.synthetic.main.log_entry_list_item.llBolus
import kotlinx.android.synthetic.main.log_entry_list_item.llCarbs
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalDose
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalDoseUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBasalMedication
import kotlinx.android.synthetic.main.log_entry_list_item.tvBgl
import kotlinx.android.synthetic.main.log_entry_list_item.tvBglUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusDose
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusDoseUnit
import kotlinx.android.synthetic.main.log_entry_list_item.tvBolusMedication
import kotlinx.android.synthetic.main.log_entry_list_item.tvCarbs
import kotlinx.android.synthetic.main.log_entry_list_item.tvTime
import org.threeten.bp.OffsetDateTime

class LogEntriesAdapter(
    private var logEntries: MutableList<Any> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_LOG_ENTRY = 1
    }

    override fun getItemCount(): Int = logEntries.size

    override fun getItemViewType(position: Int): Int {
        return when (logEntries[position]) {
            is OffsetDateTime -> TYPE_HEADER
            is LogEntry -> TYPE_LOG_ENTRY
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_header_item, parent, false)
                )
            }
            TYPE_LOG_ENTRY -> {
                LogEntryViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.log_entry_list_item, parent, false)
                )
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when (viewHolder) {
            is LogEntryViewHolder -> viewHolder.bind(logEntries[position] as LogEntry)
            is HeaderViewHolder -> viewHolder.bind(logEntries[position] as OffsetDateTime)
            else -> throw IllegalArgumentException()
        }
    }

    fun updateLogEntries(newLogEntries: List<Any>) {
        val logEntriesDiffCallback = LogEntryDiffCallback(logEntries, newLogEntries)
        val diffResult = DiffUtil.calculateDiff(logEntriesDiffCallback)

        logEntries.clear()
        logEntries.addAll(newLogEntries)
        diffResult.dispatchUpdatesTo(this)
    }

    class LogEntryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(logEntry: LogEntry) {
            tvTime.text = DateTimeUtils.getTimeForLogbook(logEntry.dateTime)

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
            }
        }

        private fun setBglFieldsVisibility(visible: Boolean) {
            llBgl.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setBasalMedFieldsVisibility(visible: Boolean) {
            llBasal.visibility = if (visible) View.VISIBLE else View.GONE
            divBasal.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setBolusMedFieldsVisibility(visible: Boolean) {
            llBolus.visibility = if (visible) View.VISIBLE else View.GONE
            divBolus.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun setCarbsFieldsVisibility(visible: Boolean) {
            llCarbs.visibility = if (visible) View.VISIBLE else View.GONE
            divCarbs.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }

    class HeaderViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(dt: OffsetDateTime) {
            tvHeader.text = DateTimeUtils.getDateForLogbook(dt)
        }
    }

    class LogEntryDiffCallback(
        private val oldLogEntries: List<Any>,
        private val newLogEntries: List<Any>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldLogEntries.size

        override fun getNewListSize(): Int = newLogEntries.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when (newLogEntries[newItemPosition]) {
                is LogEntry -> {
                    oldLogEntries[oldItemPosition] is LogEntry
                            && (oldLogEntries[oldItemPosition] as LogEntry).id == (newLogEntries[newItemPosition] as LogEntry).id
                }
                is OffsetDateTime -> {
                    oldLogEntries[oldItemPosition] is OffsetDateTime
                }
                else -> false
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when (newLogEntries[newItemPosition]) {
                is LogEntry -> {
                    oldLogEntries[oldItemPosition] as? LogEntry == newLogEntries[newItemPosition] as? LogEntry
                }
                is OffsetDateTime -> {
                    oldLogEntries[oldItemPosition] as? OffsetDateTime == newLogEntries[newItemPosition] as? OffsetDateTime
                }
                else -> false
            }
        }
    }
}
