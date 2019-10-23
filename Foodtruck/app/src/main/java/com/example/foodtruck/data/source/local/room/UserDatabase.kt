package com.example.foodtruck.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodtruck.data.source.local.model.entities.FoodieData
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.model.entities.VendorData
import com.example.foodtruck.data.source.local.room.dao.FoodieDataDao
import com.example.foodtruck.data.source.local.room.dao.UserDao
import com.example.foodtruck.data.source.local.room.dao.VendorDataDao

@Database(entities = arrayOf(User::class,VendorData::class,FoodieData::class), version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun foodieDataDao() : FoodieDataDao
    abstract fun userDao() : UserDao
    abstract fun vendorDataDao() : VendorDataDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context) : UserRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}