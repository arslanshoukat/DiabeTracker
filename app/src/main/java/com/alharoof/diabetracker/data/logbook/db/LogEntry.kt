package com.alharoof.diabetracker.data.logbook.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alharoof.diabetracker.data.logbook.model.BGLUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.data.logbook.model.Medication
import org.threeten.bp.ZonedDateTime

@Entity
data class LogEntry(
    val dateTime: ZonedDateTime,
    val bgl: Int? = null,
    val bglUnit: BGLUnit? = null,
    @Embedded(prefix = "basal")
    val basalMedication: Medication? = null,
    @Embedded(prefix = "bolus")
    val bolusMedication: Medication? = null,
    val carbs: Int? = null,
    val category: Category? = null
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}