package ezike.tobenna.myweather.data.model;

import com.squareup.moshi.Json;

public class Astro {

    @Json(name = "sunrise")
    private String sunrise;

    @Json(name = "sunset")
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}