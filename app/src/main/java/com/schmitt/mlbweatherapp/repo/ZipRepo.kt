package com.schmitt.mlbweatherapp.repo

import com.schmitt.mlbweatherapp.data.WeatherData

class ZipRepo {

    val storedData = HashMap<String, WeatherData>();

    fun getDataFromCache(zipCode: String): WeatherData? {
        return storedData[zipCode]
    }

    fun addDataToCache(zipCode: String, weatherData: WeatherData) {
        storedData[zipCode] = weatherData
    }
}