package com.example.smartflow.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.smartflow.model.Product
import com.example.smartflow.repository.SmartFlowRepository
import com.example.smartflow.utils.SmartFlowPagingSource
import com.example.smartflow.utils.SmartFlowStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import timber.log.Timber

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class SmartFlowViewModel(private val smartFlowRepository: SmartFlowRepository) : ViewModel() {
    val data = Pager(
        PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20)
    ) {
        SmartFlowPagingSource(smartFlowRepository)
    }.flow.cachedIn(viewModelScope)
   suspend fun getProducts(): List<Product> = smartFlowRepository.getProductsFromDatabase(8, 8)
}

class SmartFlowViewModelFactory(
    private val smartFlowRepository: SmartFlowRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SmartFlowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SmartFlowViewModel(smartFlowRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}