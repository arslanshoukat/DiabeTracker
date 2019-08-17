package com.alharoof.diabetracker.data.bloodglucoselevel.db

import androidx.room.Dao
import androidx.room.Query
import com.alharoof.diabetracker.data.base.BaseDao
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import io.reactivex.Flowable

@Dao
interface BloodGlucoseLevelDao : BaseDao<BloodGlucoseLevel> {

    @Query("SELECT * FROM BloodGlucoseLevel ORDER BY time DESC")
    fun getAll(): Flowable<List<BloodGlucoseLevel>>

    @Query("SELECT * FROM BloodGlucoseLevel WHERE category = :category ORDER BY time DESC")
    fun getAllByCategory(category: Category): Flowable<List<BloodGlucoseLevel>>

    @Query("DELETE FROM BloodGlucoseLevel")
    fun deleteAll()
}
