package com.schmitt.mlbweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.schmitt.mlbweatherapp.api.GeoAPI
import com.schmitt.mlbweatherapp.presentation.MainScreen
import com.schmitt.mlbweatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json


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

                val json = Json { ignoreUnknownKeys = true }
                try {
                    val zipCodeResolver = GeoAPI()
                    val geoResponse = zipCodeResolver.getLatLngFromZipCode(scopedContext, zipCode)
                    println("GEO: " + geoResponse.toString())

                } catch (e: Exception) {
                    println(e);
                }

            }
        }

    }
}


