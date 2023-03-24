package com.example.smartflow.utils

import androidx.paging.PagingConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

val pageConfig = PagingConfig(pageSize = 10, enablePlaceholders = true)

