package com.example.travel_reviews.reviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.travel_reviews.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

enum class ReviewsAPIStatus { LOADING, ERROR, DONE }

class ReviewsViewModel : ViewModel() {

    private val _status = MutableLiveData<ReviewsAPIStatus>()

    val status: LiveData<ReviewsAPIStatus>
        get() = _status

    private val _properties = MutableLiveData<PagedList<ReviewProperty>>()

    val properties: LiveData<PagedList<ReviewProperty>>
        get() = _properties

    val pagedListLiveData : LiveData<PagedList<ReviewProperty>> by lazy {
        val dataSourceFactory = ReviewsDataSourceFactory()
        val config = PagedList.Config.Builder()
            .setPageSize(LIMIT)
            .build()
        LivePagedListBuilder(dataSourceFactory, config).build()
    }

    private val _dataSource = MutableLiveData<PageKeyedDataSource<Int, ReviewProperty>>()

    val dataSource: MutableLiveData<PageKeyedDataSource<Int, ReviewProperty>>
        get() = _dataSource

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    init {

        Log.i("ReviewsDataSource", String.format("1 init is run: %s", pagedListLiveData.value))


        _properties.value = pagedListLiveData.value
    }

    /*private fun getReviewProperties(limit: Int, offset: Int) {
        coroutineScope.launch {
            val propertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync(limit, offset)
            try {
                _status.value = ReviewsAPIStatus.LOADING
                val listResult = propertiesDeferred.await()
                _status.value = ReviewsAPIStatus.DONE
                _properties.value = listResult.reviewList
                Log.i("ReviewsViewModel", String.format("1 getReviewProperties is run: %s", listResult))
            } catch (e: Exception) {
                _status.value = ReviewsAPIStatus.ERROR
                _properties.value = ArrayList()
                Log.i("ReviewsViewModel", String.format("2 getReviewProperties is run: %s", e))
            }
        }
    }*/
}