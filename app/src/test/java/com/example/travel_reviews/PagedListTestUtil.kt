package com.example.travel_reviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.travel_reviews.network.ReviewsAPI
import com.example.travel_reviews.network.ReviewsSort
import com.example.travel_reviews.utils.MainCoroutineScopeRule
import kotlinx.coroutines.launch
import org.junit.Rule

const val LIMIT = 10
private const val OFFSET = 0

fun <T> List<T>.asPagedList(config: PagedList.Config? = null, sort: ReviewsSort?): PagedList<T>? {
    val defaultConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(size)
        .setPrefetchDistance(1)
        .build()
    return LivePagedListBuilder<Int, T>(
        createMockDataSourceFactory(this, sort),
        config ?: defaultConfig
    ).build().getOrAwaitValue()
}

private fun <T> createMockDataSourceFactory(itemList: List<T>, sort: ReviewsSort?): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> = MockReviewsDataSource(itemList, sort)
    }

class MockReviewsDataSource<T>(private val itemList: List<T>, private val sort: ReviewsSort?) : PageKeyedDataSource<Int, T>() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET, sort?.value)
            try {
                val listResult = propertiesDeferred.reviewList as List<T>
                callback.onResult(listResult, null, LIMIT)
            } catch (e: Exception) {
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET, sort?.value)
            try {
                val listResult = propertiesDeferred.reviewList as List<T>
                val adjacentKey = if (!listResult.isNullOrEmpty()) params.key + com.example.travel_reviews.network.LIMIT else null
                callback.onResult(listResult, adjacentKey)
            } catch (e: Exception) {
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET, sort?.value)
            try {
                val listResult = propertiesDeferred.reviewList as List<T>
                val adjacentKey = if (params.key > 1) params.key - com.example.travel_reviews.network.LIMIT else null
                callback.onResult(listResult, adjacentKey)
            } catch (e: Exception) {
            }
        }
    }
}