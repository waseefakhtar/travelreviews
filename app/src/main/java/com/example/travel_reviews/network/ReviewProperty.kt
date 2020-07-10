package com.example.travel_reviews.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewProperty(
    val id: String,
    val datePosted: String,
    val rating: Float,
    val content: String,
    val name: String,
    val location: String): Parcelable {

    val nameAndLocation
        get() = "$name - $location"
}