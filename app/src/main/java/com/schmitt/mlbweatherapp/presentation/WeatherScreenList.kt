package com.schmitt.mlbweatherapp.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.schmitt.mlbweatherapp.WeatherListActivity
import com.schmitt.mlbweatherapp.data.List
import com.schmitt.mlbweatherapp.data.WeatherData

@Composable
fun WeatherList(modifier: Modifier = Modifier, weatherData: WeatherData) {

    val activity = LocalContext.current as WeatherListActivity



    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()) {
        Text(
            text = "Weather report for the next five days",
            modifier = modifier
        )
        MessageList(weatherData.list, activity)


    }

}

@Composable
fun MessageList(weatherDataList: ArrayList<List>, activity: WeatherListActivity) {
    Column () {
        weatherDataList.forEach { item ->
            WeatherRow(item, activity)
        }
    }
}

//List is not an ideal name
@Composable
fun WeatherRow(weatherItem: List, activity: WeatherListActivity) {

    //Add a row so we can add the underline beneath
    Row(modifier = Modifier
        .drawBehind {

            val strokeWidth = 10f
            val y = size.height

            drawLine(
                Color.Magenta,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidth
            )
        }.clickable { activity.selectedRow(weatherItem) }
    ) {
        //Use a column to stack the details
        Column {
            weatherItem.dtTxt?.let {
                Text(
                    text = it
                )
            }
            weatherItem.main?.temp?.let {
                Text(

                    text = it.toString()
                )
            }
        }
    }
}