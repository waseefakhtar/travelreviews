package com.example.travel_reviews.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

const val LIMIT = 10
private const val OFFSET = 0

class ReviewsDataSource(
    private val reviewsAPIService: ReviewsAPIService,
    private val reviewsSort: ReviewsSort?
) : PagingSource<Int, ReviewProperty>() {

    val networkState = MutableLiveData<NetworkState>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewProperty> {
        networkState.postValue(NetworkState.LOADING)
        return try {
            val response = reviewsAPIService.getReviewsAsync(LIMIT, OFFSET, reviewsSort?.value)
            val reviewList = response.reviewList
            networkState.postValue(NetworkState.SUCCESS)
            LoadResult.Page(
                data = reviewList,
                prevKey = if (params.key!! > 1) params.key!! - LIMIT else null,
                nextKey = if (!reviewList.isNullOrEmpty()) params.key?.plus(LIMIT) else null
            )
        } catch (exception: IOException) {
            networkState.postValue(NetworkState.error(exception.toString()))
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            networkState.postValue(NetworkState.error(exception.toString()))
            return LoadResult.Error(exception)
        }
    }
}