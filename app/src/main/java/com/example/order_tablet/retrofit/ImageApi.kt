package com.example.order_tablet.retrofit

import android.media.Image
import com.example.order_tablet.AppConstant
import com.example.order_tablet.ImageService
import com.example.order_tablet.model.ImageResultDataItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ImageApi {

    private var instance: Retrofit? = null
    private lateinit var service : ImageService

    private const val CONNECT_TIMEOUT_SEC = 20000L

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory()) // Kotlin 클래스를 위한 어댑터 추가
        .build()

    fun getInstance() : Retrofit {
        if(instance == null) {
            val interceptor = HttpLoggingInterceptor()


                interceptor.level = HttpLoggingInterceptor.Level.BODY

//                interceptor.level = HttpLoggingInterceptor.Level.NONE


            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(AppConstant.imageApiUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        return instance!!
    }

    fun getService(): ImageService {
        return getInstance().create(ImageService::class.java)
    }

}