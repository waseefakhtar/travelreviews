package com.example.travel_reviews.reviews

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.travel_reviews.network.*
import kotlinx.coroutines.launch

class ReviewsViewModel : ViewModel() {

    var currentSort: ReviewsSort? = null
    var dataSourceFactory = ReviewsDataSourceFactory()
    var networkState: LiveData<NetworkState>
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