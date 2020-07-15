package com.example.travel_reviews.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val LIMIT = 10
private const val OFFSET = 0

class ReviewsDataSource(private val reviewsSort: ReviewsSort?) : PageKeyedDataSource<Int, ReviewProperty>() {

    private var dataSourceJob = Job()

    private val coroutineScope = CoroutineScope(
        dataSourceJob + Dispatchers.Main )

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReviewProperty>
    ) {
        networkState.postValue(NetworkState.LOADING)
        coroutineScope.launch {
            val properties = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET, reviewsSort?.value)
            try {
                callback.onResult(properties.reviewList, null, LIMIT)
                networkState.postValue(NetworkState.SUCCESS)
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.toString()))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewProperty>) {
        networkState.postValue(NetworkState.LOADING)
        coroutineScope.launch {
            val properties = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, params.key, reviewsSort?.value)
            try {
                val adjacentKey = if (!properties.reviewList.isNullOrEmpty()) params.key + LIMIT else null
                callback.onResult(properties.reviewList, adjacentKey)
                networkState.postValue(NetworkState.SUCCESS)
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.toString()))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewProperty>) {
        networkState.postValue(NetworkState.LOADING)
        coroutineScope.launch {
            val properties = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, params.key, reviewsSort?.value)
            try {
                val adjacentKey = if (params.key > 1) params.key - LIMIT else null
                callback.onResult(properties.reviewList, adjacentKey)
                networkState.postValue(NetworkState.SUCCESS)
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.toString()))
            }
        }
    }
}