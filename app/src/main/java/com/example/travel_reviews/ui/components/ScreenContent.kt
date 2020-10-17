package com.example.travel_reviews.ui.components

import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.PagedList
import com.example.travel_reviews.network.ReviewList
import com.example.travel_reviews.network.ReviewProperty
import com.example.travel_reviews.reviews.ReviewsViewModel
import com.example.travel_reviews.ui.state.UiState

@Composable
fun ScreenContent(
    viewModel: ReviewsViewModel,
    reviews: PagedList<ReviewProperty>?,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    ReviewList(
        viewModel =  viewModel,
        reviews1 = reviews,
        modifier = modifier
    )
}

@Composable
private fun ReviewList(
    viewModel: ReviewsViewModel,
    reviews1: PagedList<ReviewProperty>?,
    modifier: Modifier = Modifier
) {
    val reviewsState = viewModel.pagedListLiveData.observeAsState()
    val reviews = reviewsState.value ?: listOf<ReviewProperty>()
    Log.i("ScreenContent", String.format("ReviewList is run: %s", reviews))
    LazyColumnFor(
        items = reviews
    ) {
        ReviewItem(it, modifier)
    }
}

@Composable
private fun ReviewItem(review: ReviewProperty, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = review.content)
    }
}