package com.example.foodtruck.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "vendor_data",
    foreignKeys = (
        arrayOf(
            ForeignKey(
                entity = User::class,
                onUpdate = ForeignKey.CASCADE,
                parentColumns = arrayOf("user_id"),
                childColumns = arrayOf("id")
            )
        )
    )
)
data class VendorData(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int
)