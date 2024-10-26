package com.schmitt.mlbweatherapp.api

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.io.IOException

class GeoAPI {
    fun getLatLngFromZipCode(context: Context, zipCode: String): Pair<Double, Double>? {
        val geocoder = Geocoder(context)
        try {

            val addresses: MutableList<Address>? = geocoder.getFromLocationName(zipCode, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    return Pair(address.latitude, address.longitude)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}