package com.example.smartflow.koin

import androidx.room.Room
import com.example.smartflow.model.database.SmartFlowDatabase
import com.example.smartflow.network.SmartFlowAPiService
import com.example.smartflow.network.SmartFlowInterceptor
import com.example.smartflow.repository.SmartFlowRepository
import com.example.smartflow.viewmodel.SmartFlowViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

val appModule = module {
    single { SmartFlowRepository(get(), get()) }
    viewModel { SmartFlowViewModel(get()) }
}

val networkModule = module {
    factory { SmartFlowInterceptor() }
    factory { SmartFlowAPiService.provideOkHttpClient(get()) }
    factory { SmartFlowAPiService.provideNetworkAPi(get()) }
    single  { SmartFlowAPiService.provideRetrofit(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
        androidContext(),
        SmartFlowDatabase::class.java,
        "smart_flow_database"
        ).build()
    }
    single { get<SmartFlowDatabase>().getSmartFlowDao() }
}

