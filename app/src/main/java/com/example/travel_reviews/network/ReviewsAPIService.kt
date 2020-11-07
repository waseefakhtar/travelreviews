package com.example.travel_reviews.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class ReviewsSort(val value: String) {
    SORT_DATE_ASC("date:asc"),
    SORT_DATE_DESC("date:desc"),
    SORT_RATING_ASC("rating:asc"),
    SORT_RATING_DESC("rating:desc")
}

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
    suspend fun getReviewsAsync(@Query("limit") limit: Int, @Query("offset") offset: Int, @Query("sort") sort: String?): ReviewList
}

object ReviewsAPI {
    val retrofitService : ReviewsAPIService by lazy {
        retrofit.create(ReviewsAPIService::class.java) }
}