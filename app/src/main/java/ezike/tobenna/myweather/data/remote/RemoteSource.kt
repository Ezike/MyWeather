package ezike.tobenna.myweather.data.remote

import ezike.tobenna.myweather.data.model.WeatherResponse


interface RemoteSource {
    suspend fun fetchWeather(location: String): WeatherResponse
}