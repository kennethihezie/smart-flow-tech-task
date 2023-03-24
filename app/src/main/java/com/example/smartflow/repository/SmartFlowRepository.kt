package com.example.smartflow.repository

import androidx.paging.Pager
import com.example.smartflow.model.Product
import com.example.smartflow.model.SmartFlowDao
import com.example.smartflow.network.ISmartFlowApi
import com.example.smartflow.utils.pageConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class SmartFlowRepository(private val iSmartFlowApi: ISmartFlowApi, private val smartFlowDao: SmartFlowDao) {
    //Get product from network and insert to database
    private suspend fun getProductsFromNetwork(): List<Product> {
        val products = iSmartFlowApi.getProducts()
        smartFlowDao.insertProducts(products)
        return products
    }

    //read from database
    suspend fun getProductsFromDatabase(limit: Int, offset: Int): List<Product> {
        val products = smartFlowDao.getAllProducts(limit, offset)

        return if(products.isNotEmpty()){
            Timber.tag("PRODUCT").d("${products.first()}")
            products
        } else {
            getProductsFromNetwork()
        }
    }

}