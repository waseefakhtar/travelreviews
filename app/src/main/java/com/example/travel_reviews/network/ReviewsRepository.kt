package com.example.travel_reviews.network

import androidx.lifecycle.LiveData
import androidx.paging.*


class ReviewsRepository(private val service: ReviewsAPIService) {

    fun getReviewList(reviewsSort: ReviewsSort?): LiveData<PagingData<ReviewProperty>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewsDataSource(service, reviewsSort) }
        ).liveData
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}