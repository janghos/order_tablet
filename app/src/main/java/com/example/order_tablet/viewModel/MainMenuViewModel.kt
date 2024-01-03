package com.example.order_tablet.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.order_tablet.model.ImageResultDataItem
import com.example.order_tablet.retrofit.ApiUtil
import com.example.order_tablet.retrofit.ImageApi
import com.example.order_tablet.retrofit.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainMenuViewModel : ViewModel() {

    lateinit var lastRequest : () -> Unit

    /**
     * get ImageResultData Info MutableLive
     */

    private val imageListInfo : MutableLiveData<List<ImageResultDataItem>> by lazy{
        MutableLiveData<List<ImageResultDataItem>>()
    }

    /**
     * ImageResultData Info Data get LiveData
     */
    fun getImageListInfoData() : LiveData<List<ImageResultDataItem>> {
        return imageListInfo
    }

    /**
     *  get ImageResultData Info Data
     */
    private fun getImageListInfoData(data: List<ImageResultDataItem>) {
        imageListInfo.postValue(data)
    }

    /**
     * request imageListInfo
     */
    fun requestImageListInfoData(page : Int, limit : Int) = viewModelScope.launch {
        lastRequest = { fetchimageListInfo(page, limit) }
        fetchimageListInfo(page, limit)
    }

    /**
     * Fetch imageListInfo
     * */
    private fun fetchimageListInfo(page : Int, limit : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val paramMap = mapOf(
                "page" to page.toString(),
                "limit" to limit.toString()
            )
            when(val result = ApiUtil.safeApiCall { ImageApi.getService().getImageList(paramMap) }) {
                is Result.Success -> {
                    getImageListInfoData(result.data)
                }

                is Result.Error -> {
                    if(result.errorData is ResponseBody) {

                    } else {
                        Log.e(ContentValues.TAG, "error message = ${result.errorData}")
                    }
                }

                else -> {

                }
            }
        }
    }
}