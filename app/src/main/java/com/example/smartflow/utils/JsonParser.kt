package com.example.smartflow.utils

import java.lang.reflect.Type

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}