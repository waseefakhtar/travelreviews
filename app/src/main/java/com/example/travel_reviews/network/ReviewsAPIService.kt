package com.example.travel_reviews.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

enum class ReviewsSort(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all") }

private const val BASE_URL = "https://travelers-api.getyourguide.com"

private val moshi = Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ReviewsAPIService {
    @GET("/activities/23776/reviews")
    fun getPropertiesAsync(@Query("limit") limit: Int, @Query("offset") offset: Int):
            Deferred<ReviewList>
}

object ReviewsAPI {
    val retrofitService : ReviewsAPIService by lazy {
        retrofit.create(ReviewsAPIService::class.java) }
}