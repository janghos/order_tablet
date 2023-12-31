package com.example.order_tablet.retrofit

import retrofit2.Response

object ApiUtil {
    // 매개변수로 suspend 메소드를 받고 통신성공 여부에 따라 Result 값을 반환하는 함수
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()

            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
            } else {
                Result.Error(myResp.errorBody()!!)
            }

        } catch (e: Exception) {
            Result.Error(e.message ?: "Internet error runs")
        }
    }

}