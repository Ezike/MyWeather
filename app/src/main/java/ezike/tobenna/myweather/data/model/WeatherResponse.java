package ezike.tobenna.myweather.data.model;

import com.squareup.moshi.Json;

import ezike.tobenna.myweather.data.local.entity.CurrentWeather;

/**
 * @author tobennaezike
 */
public class WeatherResponse {

    @Json(name = "current")
    private CurrentWeather current;

    @Json(name = "mWeatherLocation")
    private WeatherLocation mWeatherLocation;

    @Json(name = "forecast")
    private Forecast forecast;

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public WeatherLocation getWeatherLocation() {
        return mWeatherLocation;
    }

    public void setWeatherLocation(WeatherLocation weatherLocation) {
        this.mWeatherLocation = weatherLocation;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}