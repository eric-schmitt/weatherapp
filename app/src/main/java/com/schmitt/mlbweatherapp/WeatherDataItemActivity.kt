package com.schmitt.mlbweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.schmitt.mlbweatherapp.presentation.WeatherItem
import com.schmitt.mlbweatherapp.ui.theme.WeatherAppTheme
import kotlinx.serialization.json.Json
import com.schmitt.mlbweatherapp.data.List


class WeatherDataItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherDataString = intent.getStringExtra("weatherItem")
        val json = Json { ignoreUnknownKeys = true }

        //Assume we have successfully passed the string for now
        val weatherData = json.decodeFromString<List>(weatherDataString!!)

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (weatherData != null) {
                        WeatherItem(
                            modifier = Modifier.padding(innerPadding),
                            weatherData
                        )
                    }
                }
            }
        }
    }

}