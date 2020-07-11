package com.example.travel_reviews.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class ReviewList(
    @Json(name = "reviews")
    val reviewList: List<ReviewProperty>
)


@Parcelize
data class ReviewProperty(
    @Json(name = "id")
    val id: String,

    @Json(name = "created")
    val datePosted: String,

    @Json(name = "rating")
    val rating: Float,

    @Json(name = "message")
    val content: String,

    @Json(name = "author")
    val author: Author): Parcelable {

    val nameAndLocation
        get() = "${author.fullName} - ${author.location ?: ""}"
}

@Parcelize
data class Author(
    @Json(name = "fullName")
    val fullName: String,

    @Json(name = "country")
    val location: String?): Parcelable