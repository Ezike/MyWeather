package ezike.tobenna.myweather.data;

import com.squareup.moshi.Json;

/**
 * @author tobennaezike
 */
public class WeatherResponse {

    @Json(name = "current")
    private Current current;

    @Json(name = "location")
    private Location location;

    @Json(name = "forecast")
    private Forecast forecast;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}