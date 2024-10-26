package com.schmitt.mlbweatherapp.api


import java.net.HttpURLConnection
import java.net.URL

//This would be in gradle.properties, so we can modify use testing/production builds
val API_KEY: String = "89756508a30feaad046f985ef73cfaec"

class WeatherAPI {
    fun getWeather(lat: Double, lon: Double): String {
        //Construct our URL directed to openweathermap.org

        val completeURL = "https://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$lon&units=imperial&cnt=40&appid=$API_KEY"
        val connection = URL(completeURL).openConnection() as HttpURLConnection

        var data = ""
        try {
            data = connection.inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }

        return data;
    }
}