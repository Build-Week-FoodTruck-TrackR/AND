package com.example.foodtruck.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodtruck.ui.foodie.fragments.Favorites

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "user_id")
    val userId: Int,
    val username: String,
    val accountType: AccountType
)