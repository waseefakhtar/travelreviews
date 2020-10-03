package com.example.travel_reviews.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * A [MaterialTheme] for TravelReviews.
 */
@Composable
fun TravelReviewsTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        primary = Primary
    )

    MaterialTheme(colors = colors, content = content)
}