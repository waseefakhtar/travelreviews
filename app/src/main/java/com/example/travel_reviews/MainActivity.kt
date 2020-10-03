package com.example.travel_reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import com.example.travel_reviews.ui.theme.TravelReviewsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent {
            TravelReviewsApp()
        }
    }
}

@Composable
fun TravelReviewsApp() {
    TravelReviewsTheme {
        Scaffold(
            topBar = {
                val title = stringResource(id = R.string.app_name)
                TopAppBar(
                    title = { Text(text = title) }
                )
            },
            bodyContent = {},
        )
    }
}