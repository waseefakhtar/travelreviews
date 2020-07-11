package com.example.travel_reviews.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList


class ReviewsDataSourceFactory : DataSource.Factory<Int, ReviewProperty>() {

    private val _reviewsLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ReviewProperty>>()

    val reviewsLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, ReviewProperty>>
        get() = _reviewsLiveDataSource

    override fun create(): DataSource<Int, ReviewProperty> {
        val reviewsDataSource = ReviewsDataSource()

        _reviewsLiveDataSource.postValue(reviewsDataSource)

        return reviewsDataSource
    }
}