package com.example.foodtruck.data.common

import androidx.room.*

@Dao
interface BaseDao<T> {

    // Insert a object into the database. If the Item already exists, replace it.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun create(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createAll(objects: List<T>)

    //Delete obeject in database
    @Delete
    @JvmSuppressWildcards
    fun delete(obj: T)
}