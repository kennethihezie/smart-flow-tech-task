package com.example.smartflow

import android.app.Application
import com.example.smartflow.koin.appModule
import com.example.smartflow.koin.databaseModule
import com.example.smartflow.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class SmartFlowApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@SmartFlowApp)
            modules(listOf(databaseModule, appModule, networkModule))
        }
    }

}