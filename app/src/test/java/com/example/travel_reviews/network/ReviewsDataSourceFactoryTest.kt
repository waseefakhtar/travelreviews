package com.example.travel_reviews.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test

class ReviewsDataSourceFactoryTest {

    private val _reviewsLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ReviewProperty>>()
    private lateinit var reviewsDataSourceFactory: ReviewsDataSourceFactory

    @Before
    fun setupViewModel() {
        reviewsDataSourceFactory = ReviewsDataSourceFactory()
    }

    @Test
    fun `Should setup data source factory`() {
        //reviewsDataSourceFactory.create()

        //Verify on reviewsDataSourceFactory that reviewsDataSourceFactory.create() was called
    }
}