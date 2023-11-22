package com.example.weatherapp_mvi_jetpackcompose.domain.repository

import com.example.weatherapp_mvi_jetpackcompose.domain.util.Resource
import com.example.weatherapp_mvi_jetpackcompose.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}