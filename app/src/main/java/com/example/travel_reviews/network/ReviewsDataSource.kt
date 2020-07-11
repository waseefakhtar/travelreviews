package com.example.travel_reviews.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val LIMIT = 20
private const val OFFSET = 0

class ReviewsDataSource : PageKeyedDataSource<Int, ReviewProperty>() {

    private var dataSourceJob = Job()

    private val coroutineScope = CoroutineScope(
        dataSourceJob + Dispatchers.Main )

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReviewProperty>
    ) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET)
            try {
                val listResult = propertiesDeferred.await()
                callback.onResult(listResult.reviewList, null, OFFSET + 1)
                Log.i("ReviewsDataSource", String.format("1 loadInitial is run: %s", listResult.reviewList.size))
            } catch (e: Exception) {
                Log.i("ReviewsDataSource", String.format("2 loadInitial is run: %s", e))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewProperty>) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, params.key)
            try {
                val listResult = propertiesDeferred.await()
                val adjacentKey = if (!listResult.reviewList.isNullOrEmpty()) params.key + LIMIT else null

                callback.onResult(listResult.reviewList, adjacentKey)
                Log.i("ReviewsDataSource", String.format("1 loadAfter is run: %s. %s", listResult.reviewList[0].id, adjacentKey))
            } catch (e: Exception) {
                Log.i("ReviewsDataSource", String.format("2 loadAfter is run: %s", e))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewProperty>) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, params.key)
            try {
                val listResult = propertiesDeferred.await()
                val adjacentKey = if (params.key > 1) params.key - LIMIT else null

                callback.onResult(listResult.reviewList, adjacentKey)
                Log.i("ReviewsDataSource", String.format("1 loadBefore is run: %s", listResult.reviewList.size))
            } catch (e: Exception) {
                Log.i("ReviewsDataSource", String.format("2 loadBefore is run: %s", e))
            }
        }
    }


}