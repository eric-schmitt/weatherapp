package com.schmitt.mlbweatherapp

import android.content.Intent
import com.schmitt.mlbweatherapp.presentation.WeatherList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.schmitt.mlbweatherapp.data.List
import com.schmitt.mlbweatherapp.data.WeatherData
import com.schmitt.mlbweatherapp.ui.theme.WeatherAppTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WeatherListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherDataString = intent.getStringExtra("weatherData")
        val json = Json { ignoreUnknownKeys = true }

        //Assume we have successfully passed the string for now
        val weatherData = json.decodeFromString<WeatherData>(weatherDataString!!)

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherList(
                        modifier = Modifier.padding(innerPadding),
                        weatherData
                    )
                }
            }
        }
    }

    fun selectedRow(weatherItem: List) {
        //Start next Activity with Weather Data
    }
}