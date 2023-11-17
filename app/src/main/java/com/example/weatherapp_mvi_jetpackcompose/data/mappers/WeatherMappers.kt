package com.example.weatherapp_mvi_jetpackcompose.data.mappers

import com.example.weatherapp_mvi_jetpackcompose.data.remote.WeatherDTO
import com.example.weatherapp_mvi_jetpackcompose.data.remote.WeatherDataDto
import com.example.weatherapp_mvi_jetpackcompose.domain.weather.WeatherData
import com.example.weatherapp_mvi_jetpackcompose.domain.weather.WeatherInfo
import com.example.weatherapp_mvi_jetpackcompose.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperature[index]
        val weatherCode = weatherCodes[index]
        val humidity = humidity[index]
        val windSpeed = windSpeed[index]
        val pressure = pressure[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDTO.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find{
        val hour = if(now.minute < 30 ) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}