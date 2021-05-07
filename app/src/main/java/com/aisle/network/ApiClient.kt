package com.aisle.network

import android.content.Context
import com.aisle.utils.Constants
import com.aisle.utils.SharedPrefsManager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor


import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {


    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    lateinit var sharedPrefsManager: SharedPrefsManager
    fun getClient(context: Context): Retrofit? {

        sharedPrefsManager = SharedPrefsManager(context)
        if (okHttpClient == null) {
            initOkHttp(context,sharedPrefsManager)
        }

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }


    private fun initOkHttp(context: Context, sharedPrefsManager: SharedPrefsManager) {


        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                    .addHeader("Cookies","df9b865983bd04a5de2cf5017994bbbc71618565720")

            if (sharedPrefsManager != null) {
                if (!sharedPrefsManager.getValueString(Constants.TOKEN).toString().isNullOrBlank()){
                   requestBuilder.addHeader("Authorization","Bearer " +sharedPrefsManager.getValueString(Constants.TOKEN).toString())
                }
            }
            // Adding Authorization token (API Key)
            // Requests will be denied without API key

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        okHttpClient = httpClient.build()
    }
}