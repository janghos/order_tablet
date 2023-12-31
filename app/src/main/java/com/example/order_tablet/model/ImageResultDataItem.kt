package com.example.order_tablet.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ImageResultDataItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)