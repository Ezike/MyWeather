package ezike.tobenna.myweather.data.local

import ezike.tobenna.myweather.data.model.WeatherResponse


interface LocalDataSource {
    suspend fun save(weatherResponse: WeatherResponse?)
    fun hasLocationChanged(weatherResponse: WeatherResponse): Boolean
    suspend fun getWeather(): WeatherResponse
}
