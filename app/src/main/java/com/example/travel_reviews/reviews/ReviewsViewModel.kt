package com.example.travel_reviews.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.travel_reviews.network.*

class ReviewsViewModel(private val repository: ReviewsRepository) : ViewModel() {

    /*var currentSort: ReviewsSort? = null
    val networkState: LiveData<NetworkState>
    var pagedListLiveData : LiveData<PagedList<ReviewProperty>>

    init {
        pagedListLiveData = dataSourceFactory.toLiveData(currentSort)
        networkState = Transformations.switchMap(dataSourceFactory.reviewsLiveDataSource) {
            it.networkState
        }
    }*/

    fun loadData(reviewsSort: ReviewsSort? = null): LiveData<PagingData<ReviewProperty>> {
        return repository.getReviewList(reviewsSort)
    }

    /*fun updateSort(reviewsSort: ReviewsSort?) {
        currentSort = reviewsSort
        dataSourceFactory.reviewsLiveDataSource.value?.invalidate()
        pagedListLiveData = dataSourceFactory.toLiveData(currentSort)
    }*/
}