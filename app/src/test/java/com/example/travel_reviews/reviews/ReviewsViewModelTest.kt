package com.example.travel_reviews.reviews

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.travel_reviews.*
import com.example.travel_reviews.fakes.MainNetworkCompletableFake
import com.example.travel_reviews.network.*
import com.example.travel_reviews.utils.MainCoroutineScopeRule
import com.example.travel_reviews.utils.getValueForTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.amshove.kluent.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.stubbing.OngoingStubbing
import kotlin.coroutines.CoroutineContext

class ReviewsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private lateinit var reviewsViewModel: ReviewsViewModel

    private val reviewList = createReviewList()
    private val reviewsAPIService: ReviewsAPIService = mock {
        onBlocking { getPropertiesAsync(10, 0) } doReturn reviewList
    }


    @Before
    fun setupViewModel() {
        reviewsViewModel = ReviewsViewModel()
    }

    @Test
    fun `Should load initial pagedList by default`() {
        assertThat(reviewsViewModel.pagedListLiveData.getValueForTest()).isEqualTo(listOf<PagedList<ReviewProperty>>())
    }

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)


    @Test
    fun `Should load pagedList from API`() = runBlocking {
            val network = MainNetworkCompletableFake()
            reviewsViewModel = ReviewsViewModel()

            //Verify on dataSource that dataSource.loadInitial(anyOrNull(), anyOrNull()) was called
            //Verify on reviewsAPIService that reviewsAPIService.getPropertiesAsync(10, 0) was called
    }
}

fun <T : Any, R> KStubbing<T>.onBlocking(m: suspend T.() -> R)
        : OngoingStubbing<R> {
    return runBlocking { Mockito.`when`(mock.m()) }
}
