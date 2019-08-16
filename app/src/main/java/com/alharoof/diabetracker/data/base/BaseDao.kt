package com.alharoof.diabetracker.data.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import io.reactivex.Completable

interface BaseDao<T> {

    @Insert
    fun insert(obj: T): Completable

    @Insert
    fun insert(vararg obj: T): Completable

    @Update
    fun update(obj: T): Completable

    @Delete
    fun delete(obj: T): Completable
}
