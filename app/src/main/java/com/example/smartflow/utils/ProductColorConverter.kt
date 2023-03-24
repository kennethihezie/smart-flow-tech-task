package com.example.smartflow.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.smartflow.model.ProductColor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

//@ProvidedTypeConverter
class ProductColorConverter {
    @TypeConverter
    fun toProductJson(productColors: List<ProductColor>): String {
        return  Gson().toJson(
            productColors,
            object : TypeToken<List<ProductColor>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromProductJson(json: String): List<ProductColor> {
        return Gson().fromJson<ArrayList<ProductColor>>(json, object: TypeToken<List<ProductColor>>(){}.type) ?: emptyList()
    }
}

//@ProvidedTypeConverter
class ArrayListConverter {
    @TypeConverter
    fun fromStringArrayList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }
}