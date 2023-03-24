package com.example.smartflow.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

object SmartFlowAPiService {
    private const val BASE_URL = "https://makeup-api.herokuapp.com"

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideOkHttpClient(interceptor: SmartFlowInterceptor): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    fun provideNetworkAPi(retrofit: Retrofit): ISmartFlowApi = retrofit.create(ISmartFlowApi::class.java)
}