package com.example.travel_reviews.fakes

import com.example.travel_reviews.network.ReviewList
import com.example.travel_reviews.network.ReviewsAPIService
import kotlinx.coroutines.CompletableDeferred

class MainNetworkCompletableFake : ReviewsAPIService {

    private var completable = CompletableDeferred<ReviewList>()

    override suspend fun getPropertiesAsync(limit: Int, offset: Int): ReviewList = completable.await()

    fun sendCompletionToAllCurrentRequests(result: ReviewList) {
        completable.complete(result)
        completable = CompletableDeferred()
    }

    fun sendErrorToCurrentRequests(throwable: Throwable) {
        completable.completeExceptionally(throwable)
        completable = CompletableDeferred()
    }
}