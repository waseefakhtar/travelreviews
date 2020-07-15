package com.example.travel_reviews.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


class ReviewsDataSourceFactory : DataSource.Factory<Int, ReviewProperty>() {

    val reviewsLiveDataSource = MutableLiveData<ReviewsDataSource>()
    private lateinit var reviewsDataSource: ReviewsDataSource

    var reviewsSort: ReviewsSort? = null

    override fun create(): DataSource<Int, ReviewProperty> {
        reviewsDataSource = ReviewsDataSource(reviewsSort)
        reviewsLiveDataSource.postValue(reviewsDataSource)

        return reviewsDataSource
    }
}

fun ReviewsDataSourceFactory.toLiveData(reviewsSort: ReviewsSort?): LiveData<PagedList<ReviewProperty>> {
    this.reviewsSort = reviewsSort
    val config = PagedList.Config.Builder()
        .setPageSize(LIMIT)
        .build()
    return LivePagedListBuilder(this, config).build()
}