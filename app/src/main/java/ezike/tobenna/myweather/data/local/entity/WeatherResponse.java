package ezike.tobenna.myweather.data.local.entity;

import com.squareup.moshi.Json;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import ezike.tobenna.myweather.data.model.CurrentWeather;
import ezike.tobenna.myweather.data.model.Forecast;
import ezike.tobenna.myweather.data.model.WeatherLocation;

/**
 * @author tobennaezike
 */

@Entity(tableName = "weather_response")
public class WeatherResponse {

    @PrimaryKey
    private int id;

    @Json(name = "current")
    private CurrentWeather current;

    @Json(name = "location")
    private WeatherLocation location;

    @Json(name = "forecast")
    private Forecast forecast;

    public WeatherResponse() {
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public WeatherLocation getLocation() {
        return location;
    }

    public void setLocation(WeatherLocation location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}