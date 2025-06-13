package com.salmafahira0038.miniproject3.network

import com.salmafahira0038.miniproject3.model.Makeup
import com.salmafahira0038.miniproject3.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

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
    suspend fun getMakeup(
        @Header("Authorization") userId: String
    ): List<Makeup>

    @Multipart
    @POST("makeup.php")
    suspend fun postMakeup(
        @Header("Authorization") userId: String,
        @Part("produk") produk: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part,
    ): OpStatus

    @Multipart
    @POST("makeup.php")
    suspend fun updateMakeup(
        @Header("Authorization") userId: String,
        @Part("id") id: RequestBody,
        @Part("produk") produk: RequestBody,
        @Part("harga") harga: RequestBody
    ): OpStatus

    @Multipart
    @POST("makeup.php")
    suspend fun updateImage(
        @Header("Authorization") userId: String,
        @Part("id") id: RequestBody,
        @Part("produk") produk: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part,
    ): OpStatus
    @DELETE("makeup.php")
    suspend fun deleteMakeup(
        @Header("Authorization") userId: String,
        @Query("id") makeupId: String
    ): OpStatus
}


object MakeupApi{
    val service: MakeupApiService by lazy {
        retrofit.create(MakeupApiService::class.java)
    }

    fun getMakeupUrl(imageId: String): String{
        return  "${BASE_URL}image.php?id=$imageId"
    }
}

enum class  ApiStatus { LOADING, SUCCESS, FAILED }