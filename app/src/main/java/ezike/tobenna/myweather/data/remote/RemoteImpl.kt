package ezike.tobenna.myweather.data.remote

import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.data.remote.api.ApiService
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val apiService: ApiService) : RemoteSource {

    override suspend fun fetchWeather(location: String): WeatherResponse {
        return apiService.getWeather(location)
    }
}