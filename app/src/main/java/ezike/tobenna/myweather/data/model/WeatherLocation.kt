package ezike.tobenna.myweather.data.model


import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.ZonedDateTime.ofInstant
import org.threeten.bp.format.TextStyle
import java.util.*

@JsonClass(generateAdapter = true)
data class WeatherLocation(
        @Json(name = "country")
        val country: String,
        @Json(name = "lat")
        val lat: String,
        @Json(name = "localtime")
        val localtime: String,
        @Json(name = "localtime_epoch")
        val localtimeEpoch: Long,
        @Json(name = "lon")
        val lon: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "region")
        val region: String,
        @Json(name = "timezone_id")
        val timezoneId: String,
        @Json(name = "utc_offset")
        val utcOffset: String
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getZonedDateTime(): ZonedDateTime {
        val instant = Instant.ofEpochSecond(localtimeEpoch);
        val zoneId = ZoneId.of(timezoneId);
        return ofInstant(instant, zoneId);
    }

    fun getDay(): String {
        val dow = LocalDate.now(ZoneId.of(timezoneId)).dayOfWeek;
        return dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}