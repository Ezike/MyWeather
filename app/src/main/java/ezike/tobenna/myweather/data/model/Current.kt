package ezike.tobenna.myweather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Current(
        @Json(name = "cloudcover")
        val cloudCover: Double,
        @Json(name = "feelslike")
        val feelsLike: Double,
        @Json(name = "humidity")
        val humidity: Double,
        @Json(name = "is_day")
        val isDay: String,
        @Json(name = "observation_time")
        val observationTime: String,
        @Json(name = "precip")
        val precipitation: Double,
        @Json(name = "pressure")
        val pressure: Double,
        @Json(name = "temperature")
        val temperature: Double,
        @Json(name = "uv_index")
        val uvIndex: Double,
        @Json(name = "visibility")
        val visibility: Double,
        @Json(name = "weather_code")
        val weatherCode: Double,
        @Json(name = "weather_descriptions")
        val weatherDescriptions: List<String>,
        @Json(name = "weather_icons")
        val weatherIcons: List<String>,
        @Json(name = "wind_degree")
        val windDegree: Double,
        @Json(name = "wind_dir")
        val windDir: String,
        @Json(name = "wind_speed")
        val windSpeed: Double
)