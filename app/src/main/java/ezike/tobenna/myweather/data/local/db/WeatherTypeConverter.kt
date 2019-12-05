package ezike.tobenna.myweather.data.local.db

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi.Builder
import ezike.tobenna.myweather.data.model.Current
import ezike.tobenna.myweather.data.model.Request
import ezike.tobenna.myweather.data.model.WeatherLocation
import ezike.tobenna.myweather.data.model.WeatherResponse
import java.io.IOException

/**
 * @author tobennaezike
 */
class WeatherTypeConverter {
    private val moshi = Builder().build()

    @TypeConverter
    fun weatherToJson(weather: WeatherResponse?): String? {
        return if (weather == null) null else moshi.adapter<Any>(WeatherResponse::class.java).toJson(weather)
    }

    @TypeConverter
    fun weatherFromJson(string: String): WeatherResponse? {
        val weather: WeatherResponse?
        weather = try {
            moshi.adapter<WeatherResponse>(WeatherResponse::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return weather
    }

    @TypeConverter
    fun locationToJson(weatherLocation: WeatherLocation?): String? {
        return if (weatherLocation == null) null else moshi.adapter<Any>(WeatherLocation::class.java).toJson(weatherLocation)
    }

    @TypeConverter
    fun locationFromJson(string: String?): WeatherLocation? {
        val location: WeatherLocation?
        location = try {
            moshi.adapter<WeatherLocation>(WeatherLocation::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return location
    }

    @TypeConverter
    fun currentToJson(current: Current?): String? {
        return if (current == null) null else moshi.adapter<Any>(Current::class.java).toJson(current)
    }

    @TypeConverter
    fun currentFromJson(string: String): Current? {
        val current: Current?
        current = try {
            moshi.adapter<Current>(Current::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return current
    }

    @TypeConverter
    fun requestToJson(request: Request?): String? {
        return if (request == null) null else moshi.adapter<Any>(Request::class.java).toJson(request)
    }

    @TypeConverter
    fun requestFromJson(string: String): Request? {
        val request: Request?
        request = try {
            moshi.adapter<Request>(Request::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return request
    }
}