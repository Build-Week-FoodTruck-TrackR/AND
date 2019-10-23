package com.example.foodtruck.data.common

import androidx.room.*

@Dao
interface BaseDao<T> {

    // Insert a object into the database. If the Item already exists, replaces it.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(obj: T)

    // Updates an objects parameters in the database if it already exists.
    @Update
    @JvmSuppressWildcards
    suspend fun update(obj: T)

    //Delete obeject in database
    @Delete
    @JvmSuppressWildcards
    suspend fun delete(obj: T)
}