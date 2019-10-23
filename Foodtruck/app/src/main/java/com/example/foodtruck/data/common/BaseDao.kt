package com.example.foodtruck.data.common

import androidx.room.*

@Dao
interface BaseDao<T> {

    // Insert a object into the database. If the Item already exists, aborts.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    @JvmSuppressWildcards
    suspend fun insert(obj: T)

    // Updates existing object in database
    @Update
    @JvmSuppressWildcards
    suspend fun update(obj: T)

    //Delete obeject in database
    @Delete
    @JvmSuppressWildcards
    suspend fun delete(obj: T)
}