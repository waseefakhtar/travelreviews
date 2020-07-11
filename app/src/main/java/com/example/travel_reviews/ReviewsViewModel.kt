package com.example.travel_reviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travel_reviews.network.ReviewProperty
import com.example.travel_reviews.network.ReviewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import java.util.logging.Logger

enum class ReviewsAPIStatus { LOADING, ERROR, DONE }

class ReviewsViewModel : ViewModel() {

    private val _status = MutableLiveData<ReviewsAPIStatus>()

    val status: LiveData<ReviewsAPIStatus>
        get() = _status

    private val _properties = MutableLiveData<List<ReviewProperty>>()

    val properties: LiveData<List<ReviewProperty>>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getReviewProperties()
    }


    private fun getReviewProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = ReviewsAPI.retrofitService.getPropertiesAsync()
            try {
                _status.value = ReviewsAPIStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = ReviewsAPIStatus.DONE
                _properties.value = listResult.reviewList
                Log.i("ReviewsViewModel", String.format("1 getReviewProperties is run: %s", listResult))
            } catch (e: Exception) {
                _status.value = ReviewsAPIStatus.ERROR
                _properties.value = ArrayList()
                Log.i("ReviewsViewModel", String.format("2 getReviewProperties is run: %s", e))
            }
        }
    }

}