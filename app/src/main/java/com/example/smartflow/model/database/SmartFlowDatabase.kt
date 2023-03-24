package com.example.smartflow.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.smartflow.model.Product
import com.example.smartflow.model.SmartFlowDao
import com.example.smartflow.utils.ArrayListConverter
import com.example.smartflow.utils.ProductColorConverter

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

@TypeConverters(ProductColorConverter::class, ArrayListConverter::class)
@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class SmartFlowDatabase : RoomDatabase() {
    abstract fun getSmartFlowDao(): SmartFlowDao

    companion object {
        @Volatile
        private var INSTANCE: SmartFlowDatabase? = null
        fun getDatabase(context: Context): SmartFlowDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmartFlowDatabase::class.java,
                    "smart_flow_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}