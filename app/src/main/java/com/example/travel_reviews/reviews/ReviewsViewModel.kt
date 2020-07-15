package com.example.travel_reviews.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.travel_reviews.network.*

class ReviewsViewModel : ViewModel() {

    var currentSort: ReviewsSort? = null
    var dataSourceFactory = ReviewsDataSourceFactory()
    val networkState: LiveData<NetworkState>
    var pagedListLiveData : LiveData<PagedList<ReviewProperty>>

    init {
        pagedListLiveData = dataSourceFactory.toLiveData(currentSort)
        networkState = Transformations.switchMap(dataSourceFactory.reviewsLiveDataSource) {
            it.networkState
        }
    }

    fun updateSort(reviewsSort: ReviewsSort?) {
        currentSort = reviewsSort
        dataSourceFactory.reviewsLiveDataSource.value?.invalidate()
        pagedListLiveData = dataSourceFactory.toLiveData(currentSort)
    }
}