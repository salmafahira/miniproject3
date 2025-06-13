package com.salmafahira0038.miniproject3.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject3.model.Makeup
import com.salmafahira0038.miniproject3.network.ApiStatus
import com.salmafahira0038.miniproject3.network.MakeupApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream


class MainViewModel : ViewModel(){

    var data = mutableStateOf(emptyList<Makeup>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set


    fun retrieveData(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = MakeupApi.service.getMakeup(userId)
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveData(userId: String, produk: String, harga: String, bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = MakeupApi.service.postMakeup(
                    userId,
                    produk.toRequestBody("text/plain".toMediaTypeOrNull()),
                    harga.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )

                if(result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun updateData(userId: String, id: String, produk: String, harga: String, bitmap: Bitmap?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = if (bitmap != null) {
                    MakeupApi.service.updateImage(
                        userId,
                        id.toRequestBody("text/plain".toMediaTypeOrNull()),
                        produk.toRequestBody("text/plain".toMediaTypeOrNull()),
                        harga.toRequestBody("text/plain".toMediaTypeOrNull()),
                        bitmap.toMultipartBody()
                    )
                } else {
                    MakeupApi.service.updateMakeup(
                        userId,
                        id.toRequestBody("text/plain".toMediaTypeOrNull()),
                        produk.toRequestBody("text/plain".toMediaTypeOrNull()),
                        harga.toRequestBody("text/plain".toMediaTypeOrNull()),
                    )
                }

                if (result.status == "success") {
                    retrieveData(userId)
                } else {
                    throw Exception(result.message)
                }
            } catch (e: Exception) {
                errorMessage.value = "Error saat update: ${e.message}"
            }
        }
    }

    fun deleteData(userId: String, idMakeup: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = MakeupApi.service.deleteMakeup(userId, idMakeup)
                if (result.status == "success") {
                    retrieveData(userId)
                } else {
                    throw Exception(result.message)
                }
            } catch (e: Exception) {
                Log.d("MainViewModel", "Delete Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part{
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(),0,byteArray.size)
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody)
    }

    fun clearMessage(){ errorMessage.value = null}
}
