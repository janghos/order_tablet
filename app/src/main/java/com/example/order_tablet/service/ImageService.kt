package com.example.order_tablet.service

import com.example.order_tablet.model.ImageResultData
import com.example.order_tablet.model.ImageResultDataItem
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ImageService {

    @GET("v2/list")
    suspend fun getImageList(@QueryMap params: Map<String, String>): Response<List<ImageResultDataItem>>

}