package com.example.order_tablet.retrofit

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val errorData: Any) : Result<Nothing>()
}