package com.example.smartflow.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

@Dao
interface SmartFlowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<Product>)

    @Query("SELECT * FROM product ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllProducts(limit: Int, offset: Int): List<Product>
}