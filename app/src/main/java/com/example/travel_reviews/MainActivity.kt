package com.example.travel_reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview

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
    /*Scaffold(
        topBar = {
            TravelReviewsTopAppBar(
                allScreens = allScreens,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        }
    )*/
    Text(text = "Hello World")
}