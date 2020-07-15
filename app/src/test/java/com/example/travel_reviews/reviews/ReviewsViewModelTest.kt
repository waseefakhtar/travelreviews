package com.example.travel_reviews.reviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.example.travel_reviews.*
import com.example.travel_reviews.fakes.MainNetworkCompletableFake
import com.example.travel_reviews.network.*
import com.example.travel_reviews.utils.MainCoroutineScopeRule
import com.example.travel_reviews.utils.getValueForTest
import kotlinx.coroutines.*
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReviewsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private lateinit var reviewsViewModel: ReviewsViewModel

    @Before
    fun setupViewModel() {
        reviewsViewModel = ReviewsViewModel()
    }

    @Test
    fun `Should load pagedList from API`() {
        runBlocking {
            val network = MainNetworkCompletableFake()
            reviewsViewModel = ReviewsViewModel()
        }
    }
}
