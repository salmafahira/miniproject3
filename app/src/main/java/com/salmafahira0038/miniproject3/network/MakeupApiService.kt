package com.salmafahira0038.miniproject3.network

import com.salmafahira0038.miniproject3.model.MakeUp
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

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
}

object MakeupApi{
    val service: MakeupApiService by lazy {
        retrofit.create(MakeupApiService::class.java)
    }
}