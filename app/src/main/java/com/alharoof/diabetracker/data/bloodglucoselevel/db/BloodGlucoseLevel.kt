package com.alharoof.diabetracker.data.bloodglucoselevel.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alharoof.diabetracker.data.bloodglucoselevel.model.BGLUnit
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import org.threeten.bp.ZonedDateTime

@Entity
data class BloodGlucoseLevel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val level: Float, // blood glucose level
    val unit: BGLUnit,
    val time: ZonedDateTime,
    val category: Category
)