package com.alharoof.diabetracker.data.logbook.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alharoof.diabetracker.data.logbook.model.BGLUnit
import com.alharoof.diabetracker.data.logbook.model.Category
import org.threeten.bp.ZonedDateTime

@Entity
data class LogEntry(
    val bgl: Int,
    val unit: BGLUnit,
    val dateTime: ZonedDateTime,
    val category: Category
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}