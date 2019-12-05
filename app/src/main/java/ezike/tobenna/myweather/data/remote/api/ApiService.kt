package ezike.tobenna.myweather.data.remote.api

import ezike.tobenna.myweather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current")
    suspend fun getWeather(
            @Query("query") location: String): WeatherResponse
}