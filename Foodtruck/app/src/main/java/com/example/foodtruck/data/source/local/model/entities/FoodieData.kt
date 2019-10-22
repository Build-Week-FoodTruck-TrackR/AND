package com.example.foodtruck.data.source.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "foodie_data",
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
data class FoodieData(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int
)