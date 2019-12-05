package ezike.tobenna.myweather.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "weather_response")
data class WeatherResponse(
        @PrimaryKey
        val id: Int = 0,
        @Json(name = "current")
        val current: Current,
        @Json(name = "location")
        val weatherLocation: WeatherLocation,
        @Json(name = "request")
        val request: Request
)