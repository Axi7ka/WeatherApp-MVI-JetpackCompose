package com.example.weatherapp_mvi_jetpackcompose.domain.location

import android.location.Location


interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}