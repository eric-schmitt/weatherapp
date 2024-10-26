package com.schmitt.mlbweatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schmitt.mlbweatherapp.data.List

@Composable
fun WeatherItem(modifier: Modifier = Modifier, weatherItem: List) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()) {
        Text(
            text = "Weather report for " + weatherItem.dtTxt,
            modifier = modifier
        )
        WeatherReport(weatherItem)
    }

}

@Composable
fun WeatherReport(weatherItem: List) {
    //List item data with Elvis operators incase something doesn't come through
    Column () {
        Text("Temp: " + (weatherItem.main?.temp ?: ""))
        Text("Min Temp: " + (weatherItem.main?.tempMin ?: ""))
        Text("Max Temp: " + (weatherItem.main?.tempMax ?: ""))
        Text("Feels Like: " + (weatherItem.main?.feelsLike ?: ""))
        Text("Wind Speed: " + (weatherItem.wind?.speed ?: ""))
    }
}
