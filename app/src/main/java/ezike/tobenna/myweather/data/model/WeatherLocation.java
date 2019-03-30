package ezike.tobenna.myweather.data.model;

import android.annotation.TargetApi;
import android.os.Build;

import com.squareup.moshi.Json;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.TextStyle;

import java.util.Locale;

/**
 * @author tobennaezike
 */

public class WeatherLocation {

    @Json(name = "localtime")
    private String localtime;

    @Json(name = "country")
    private String country;

    @Json(name = "localtime_epoch")
    private int localtimeEpoch;

    @Json(name = "name")
    private String name;

    @Json(name = "lon")
    private double longitude;

    @Json(name = "region")
    private String region;

    @Json(name = "lat")
    private double latitude;

    @Json(name = "tz_id")
    private String tzId;

    public WeatherLocation(String localtime, String country, int localtimeEpoch, String name,
                           double longitude, String region, double latitude, String tzId) {
        this.localtime = localtime;
        this.country = country;
        this.localtimeEpoch = localtimeEpoch;
        this.name = name;
        this.longitude = longitude;
        this.region = region;
        this.latitude = latitude;
        this.tzId = tzId;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public void setLocaltimeEpoch(int localtimeEpoch) {
        this.localtimeEpoch = localtimeEpoch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTzId() {
        return tzId;
    }

    public void setTzId(String tzId) {
        this.tzId = tzId;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public ZonedDateTime getZonedDateTime() {
        Instant instant = Instant.ofEpochSecond(localtimeEpoch);
        ZoneId zoneId = ZoneId.of(tzId);
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    public String getDay() {
        DayOfWeek dow = LocalDate.now(ZoneId.of(tzId)).getDayOfWeek();
        return dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}