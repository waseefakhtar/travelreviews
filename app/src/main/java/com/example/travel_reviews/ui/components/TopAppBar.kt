package com.example.travel_reviews.ui.components

import androidx.compose.foundation.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TravelReviewsTopAppBar() {
    TopAppBar(
        title = { Text(text = "Travel Reviews") })
}