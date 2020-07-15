package com.example.travel_reviews

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.travel_reviews.network.ReviewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val LIMIT = 10
private const val OFFSET = 0

fun <T> List<T>.asPagedList(config: PagedList.Config? = null): PagedList<T>? {
    val defaultConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(size)
        .setPrefetchDistance(1)
        .build()
    return LivePagedListBuilder<Int, T>(
        createMockDataSourceFactory(this),
        config ?: defaultConfig
    ).build().getOrAwaitValue()
}

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> = MockReviewsDataSource(itemList)
    }

class MockReviewsDataSource<T>(private val itemList: List<T>) : PageKeyedDataSource<Int, T>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        /*coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(LIMIT, OFFSET)
            try {
                val listResult = propertiesDeferred.await()
                callback.onResult(listResult.reviewList as List<T>, null, LIMIT)
            } catch (e: Exception) {
            }
        }*/

        callback.onResult(itemList, null, LIMIT)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        callback.onResult(itemList, randomInt())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        callback.onResult(itemList, randomInt())
    }

}