package com.example.travel_reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.travel_reviews.network.ReviewProperty
import com.example.travel_reviews.reviews.ReviewsViewModel
import com.example.travel_reviews.ui.components.ScreenContent
import com.example.travel_reviews.ui.theme.TravelReviewsTheme

class MainActivity : AppCompatActivity() {

    private val viewModel: ReviewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent {
            viewModel?.let {
                TravelReviewsApp(viewModel = it)
            }
        }
    }
}

@Composable
fun TravelReviewsApp(viewModel: ReviewsViewModel) {
    TravelReviewsTheme {
        Scaffold(
            topBar = {
                val title = stringResource(id = R.string.app_name)
                TopAppBar(
                    title = { Text(text = title) }
                )
            },
            bodyContent = { innerPadding ->
                val modifier = Modifier.padding(innerPadding)

                ScreenContent(
                    viewModel = viewModel,
                    reviews = null,
                    onRefresh = {

                    },
                    modifier)

            },
        )
    }
}