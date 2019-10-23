package com.example.foodtruck.data.source.local.room.dao

import androidx.room.Dao
import com.example.foodtruck.data.common.BaseDao
import com.example.foodtruck.data.source.local.model.entities.FoodieData

@Dao
interface FoodieDataDao : BaseDao<FoodieData> {
}