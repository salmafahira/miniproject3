package com.salmafahira0038.miniproject3.network

import androidx.compose.material3.Scaffold
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://store.sthresearch.site/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MakeupApiService {
    @GET("makeup.php")
    suspend fun getMakeup(): String
}

object MakeupApi{
    val service: MakeupApiService by lazy {
        retrofit.create(MakeupApiService::class.java)
    }
}