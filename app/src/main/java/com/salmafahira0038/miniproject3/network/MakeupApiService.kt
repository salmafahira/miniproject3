package com.salmafahira0038.miniproject3.network

import com.salmafahira0038.miniproject3.model.MakeUp
import com.salmafahira0038.miniproject3.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://store.sthresearch.site/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MakeupApiService {
    @GET("makeup.php")
    suspend fun getMakeup(): List<MakeUp>

    @Multipart
    @POST("makeup.php")
    suspend fun postMakeUp(
        @Header("Authorization") userId: String,
        @Part("judul") judul: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part imageId: MultipartBody.Part
    ): OpStatus
}


object MakeupApi{
    val service: MakeupApiService by lazy {
        retrofit.create(MakeupApiService::class.java)
    }

    fun getMakeUpUrl(imageId: String): String{
        return  "${BASE_URL}image.php?id=$imageId"
    }
}

enum class  ApiStatus { LOADING, SUCCESS, FAILED }