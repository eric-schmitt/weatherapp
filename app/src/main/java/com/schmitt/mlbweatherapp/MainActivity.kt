package com.schmitt.mlbweatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.schmitt.mlbweatherapp.api.GeoAPI
import com.schmitt.mlbweatherapp.api.WeatherAPI
import com.schmitt.mlbweatherapp.data.WeatherData
import com.schmitt.mlbweatherapp.presentation.MainScreen
import com.schmitt.mlbweatherapp.repo.ZipRepo
import com.schmitt.mlbweatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val zipRepo = ZipRepo()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    fun processGeoLocation(zipCode:String) {

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        //Provide a context which is needed for GeoLocation
        val scopedContext = this

        GlobalScope.launch {
            withContext(Dispatchers.IO + coroutineExceptionHandler) {
                //Calls the Geolocation package to resolve lat/lon from zip code
                var weatherData: WeatherData
                val json = Json { ignoreUnknownKeys = true }
                try {
                    val zipCodeResolver = GeoAPI()
                    val geoResponse = zipCodeResolver.getLatLngFromZipCode(scopedContext, zipCode)
                    println("GEO: " + geoResponse.toString())

                    if (geoResponse != null) {
                        //Response is a Pair <lat, lon>
                        val lat: Double = geoResponse.first
                        val lon: Double = geoResponse.second

                        val weatherAPI = WeatherAPI()
                        val weatherResponse = weatherAPI.getWeather(lat, lon)

                        weatherData = json.decodeFromString<WeatherData>(weatherResponse)
                        zipRepo.addDataToCache(zipCode, weatherData)

                    } else {
                        //Prompt error if geoResponse is null
                        println("Error: Could not fetch zip code")
                    }
                } catch (e: Exception) {
                    println(e);
                }

                val repoWeatherData = zipRepo.getDataFromCache(zipCode);
                println(repoWeatherData)
            }
        }

    }
}


