package com.example.foodtruck.data.source.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodtruck.data.source.local.model.AccountType

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name= "account_type")
    val accountType: AccountType
)