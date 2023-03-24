package com.example.smartflow.network

import com.example.smartflow.model.Product
import retrofit2.http.GET

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

interface ISmartFlowApi {
    @GET("/api/v1/products.json")
    suspend fun getProducts(): List<Product>
}

