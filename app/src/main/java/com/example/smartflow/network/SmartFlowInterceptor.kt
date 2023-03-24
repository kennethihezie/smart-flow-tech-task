package com.example.smartflow.network

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class SmartFlowInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}