package ezike.tobenna.myweather.data.local

import ezike.tobenna.myweather.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun save(weatherResponse: WeatherResponse?)
    fun hasLocationChanged(weatherResponse: WeatherResponse): Boolean
    fun getWeather(): Flow<WeatherResponse>
}
