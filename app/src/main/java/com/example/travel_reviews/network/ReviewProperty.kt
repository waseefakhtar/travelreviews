package com.example.travel_reviews.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class ReviewList(
    @Json(name = "reviews")
    val reviewList: List<ReviewProperty>
)


@Parcelize
data class ReviewProperty(
    @Json(name = "id")
    val id: String,

    @Json(name = "created")
    val created: String,

    @Json(name = "rating")
    val rating: Float,

    @Json(name = "message")
    val content: String,

    @Json(name = "author")
    val author: Author): Parcelable {

    val nameAndLocation
        get() = "${author.fullName} - ${author.location ?: ""}"

    @IgnoredOnParcel
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale("en", "US"))

    @IgnoredOnParcel
    val outputFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale("en", "US"))

    @IgnoredOnParcel
    val date = inputFormat.parse(created)

    val datePosted: String?
        get() = outputFormat.format(date) ?: ""
}

@Parcelize
data class Author(
    @Json(name = "fullName")
    val fullName: String,

    @Json(name = "country")
    val location: String?): Parcelable