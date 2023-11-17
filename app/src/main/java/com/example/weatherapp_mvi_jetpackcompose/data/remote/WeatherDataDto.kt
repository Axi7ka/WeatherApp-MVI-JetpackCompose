package com.example.weatherapp_mvi_jetpackcompose.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: List<String>,
    @field:Json(name="temperature_2m")
    val temperature: List<Double>,
    @field:Json(name="weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name="relative_humidity_2m")
    val humidity: List<Double>,
    @field:Json(name="wind_speed_10m")
    val windSpeed: List<Double>,
    @field:Json(name="pressure_msl")
    val pressure: List<Double>
)