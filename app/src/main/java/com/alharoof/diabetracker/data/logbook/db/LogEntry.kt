package com.alharoof.diabetracker.data.logbook.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.Medication
import org.threeten.bp.OffsetDateTime

@Entity
data class LogEntry(
    val dateTime: OffsetDateTime,
    val bgl: Int? = null,
    val bglUnit: BglUnit? = null,
    @Embedded(prefix = "basal_")
    val basalMedication: Medication? = null,
    @Embedded(prefix = "bolus_")
    val bolusMedication: Medication? = null,
    val carbs: Int? = null,
    val category: Category? = null
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}